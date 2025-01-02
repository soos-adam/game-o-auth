package sadama.oauth.goa.html.templater;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Templater {
    private final static Logger LOG = LoggerFactory.getLogger(Templater.class);
    private final static Pattern INCLUDE_PATTERN = Pattern.compile("goa:include (\\w+\\.html)");

    public static void main(String[] args) throws Exception {
        if (args.length != 3) {
            LOG.warn("Usage of logger: java -cp <templater-jar> sadama.oauth.goa.html.templater.Templater LOCATION_TO_RUN_TEMPLATING_ON TEMPLATES_DIR OUTPUT_DIR");
            System.exit(1);
        }

        File root = new File(args[0]);
        File templatesDirectory = new File(args[1]);
        File destination = new File(args[2]);


        List<File> htmlFiles;
        try (Stream<Path> stream = Files.walk(root.toPath())) {
            // Filter for .html files and collect the result into a list
            htmlFiles = stream
                    .filter(path -> Files.isRegularFile(path) && path.toString().toLowerCase().endsWith(".html"))
                    .map(Path::toFile)
                    .collect(Collectors.toList());
        }
        Map<String, NodeList> templates = new HashMap<>();
        List<File> templateFiles = Arrays.stream(templatesDirectory.listFiles(
                    (dir, name) -> name.toLowerCase().endsWith(".html")))
                .collect(Collectors.toList());
        templateFiles.forEach(file -> {
            try {
                //This presumes the presence of the root element
                templates.put(file.getName(), getNodes(file).item(0).getChildNodes());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        for (File f: htmlFiles) {
            List<Document> sideEffect = new ArrayList<>();
            Map<Node, String> includes = getGOACommentsFromXml(f, sideEffect);
            Document document = sideEffect.get(0);
            if (!includes.isEmpty()) {
                includes.keySet().forEach(key -> {
                    Node parent = key.getParentNode();
                    if (!templates.containsKey(includes.get(key))) {
                        throw new RuntimeException("There is no %s template".formatted(includes.get(key)));
                    }
                    NodeList template = templates.get(includes.get(key));
                    for (int i = 0; i < template.getLength(); i++) {
                        Node newNode = document.importNode(template.item(i), true);
                        parent.insertBefore(newNode, key);
                    }
                    parent.removeChild(key);
                });
            }
            File destinationFile = getDestinationFile(f, destination);
            writeDocumentToFile(document, destinationFile.toPath().toString());
        }
    }

    private static File getDestinationFile(File f, File destination) {
        Path filePath = f.toPath();
        int resourcesIndex = -1;
        for (int i = filePath.getNameCount() - 1; i >= 0; i--) {
            if (filePath.getName(i).toString().equals("resources")) {
                resourcesIndex = i;
                break;
            }
        }
        if (resourcesIndex == -1) {
            throw new RuntimeException("No resources directory found in for %s".formatted(f.getName()));
        }
        Path resourcePath = filePath.subpath(resourcesIndex + 1, filePath.getNameCount());
        File destinationFile = destination.toPath().resolve(resourcePath).toFile();
        destinationFile.mkdirs();
        if (destinationFile.exists()) {
            destinationFile.delete();
        }
        return destinationFile;
    }

    private static NodeList getNodes(File file) throws Exception {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(file);
        document.getDocumentElement().normalize();
        return document.getChildNodes();
    }

    // Method to collect all comments from an XML file
    private  static Map<Node, String> getGOACommentsFromXml(File inputFile, List<Document> sideEffect) throws Exception {
        Map<Node, String> comments = new HashMap<>();
        NodeList nodeList = getNodes(inputFile);
        sideEffect.add(nodeList.item(0).getOwnerDocument());
        // Recursively check all nodes for comments
        collectComments(nodeList, comments);

        return comments;
    }

    private static void collectComments(NodeList nodeList, Map<Node, String> comments) {
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);

            // Check if the node is a comment
            if (node.getNodeType() == Node.COMMENT_NODE) {
                String content = node.getNodeValue().trim();
                Optional<String> goaInclude = INCLUDE_PATTERN.matcher(content)
                        .results()
                        .map(match -> match.group(1))
                        .findFirst();
                if (goaInclude.isPresent()) {
                    comments.put(node, goaInclude.get());
                }
            }

            if (node.hasChildNodes()) {
                collectComments(node.getChildNodes(), comments);
            }
        }
    }



    private static void writeDocumentToFile(Document doc, String filePath) throws Exception {
        // Create a TransformerFactory
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();

        // Set output properties (optional, for better formatting)
        transformer.setOutputProperty(OutputKeys.INDENT, "yes"); // Adds indentation for readability

        // Create a DOMSource from the Document object
        DOMSource source = new DOMSource(doc);

        // Create a StreamResult to write to a file
        FileWriter writer = new FileWriter(filePath);
        StreamResult result = new StreamResult(writer);

        // Transform the Document to the file
        transformer.transform(source, result);

        // Close the writer to release the resources
        writer.close();
    }


}
