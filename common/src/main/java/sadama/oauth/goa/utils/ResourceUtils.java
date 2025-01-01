package sadama.oauth.goa.utils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public final class ResourceUtils {
    private ResourceUtils() {}

    public static InputStream readResourceFile(String resourceFilePath) {
        if (resourceFilePath.startsWith("/")) {
            resourceFilePath = resourceFilePath.substring(1);
        }
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        return classloader.getResourceAsStream(resourceFilePath);
    }

    public static String readResourceFileContent(String resourceFilePath) throws IOException {
        return readResourceFileContent(resourceFilePath, StandardCharsets.UTF_8);
    }

    public static String readResourceFileContent(String resourceFilePath, Charset charset) throws IOException {
        InputStream is = readResourceFile(resourceFilePath);
        return new String(is.readAllBytes(), charset);
    }

}
