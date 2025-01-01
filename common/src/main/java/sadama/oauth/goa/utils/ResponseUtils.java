package sadama.oauth.goa.utils;

import org.springframework.http.ResponseEntity;

/**
 * Utility class to help create Spring ResponseEntity objects
 */
public class ResponseUtils {

    /**
     * Create a Spring response entity with the given data
     * @param data the response data
     * @return spring response entity
     * @param <T> the type of the response entity
     */
    public static <T> ResponseEntity<T> createResponse(T data) {
        return createResponse(data, "", 200);
    }

    /**
     * Create a Spring OK response entity with the given data. Type is explicitly defined for
     * from the file name where Spring does not arbitrate it correctly.
     * @param data the response data
     * @param fileName the file name where the resource is loaded from
     * @return spring response entity
     * @param <T> the type of the response entity
     */
    public static <T> ResponseEntity<T> createResponse(T data, String fileName) {
        return createResponse(data, fileName, 200);
    }

    /**
     * Create a Spring response entity with the given data and status code. Type is explicitly defined for
     * from the file name where Spring does not arbitrate it correctly.
     * @param data the response data
     * @param fileName the file name where the resource is loaded from
     * @param statusCode the status code of the response
     * @return spring response entity
     * @param <T> the type of the response entity
     */
    public static <T> ResponseEntity<T> createResponse(T data, String fileName, int statusCode) {
        ResponseEntity.BodyBuilder response = ResponseEntity.status(statusCode);
        if (fileName.endsWith(".svg")) {
            response.header("content-type", "image/svg+xml");
        }
        return response.body(data);
    }

}
