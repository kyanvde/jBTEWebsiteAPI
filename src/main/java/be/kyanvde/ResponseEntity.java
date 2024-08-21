package be.kyanvde;

/**
 * @author kyanv
 * @file ResponseEntity.java
 * @description A response wrapper object.
 * @created 21/08/2024
 */

public class ResponseEntity<T> {
    private final int statusCode;
    private final T body;

    public ResponseEntity(int statusCode, T body) {
        this.statusCode = statusCode;
        this.body = body;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public T getBody() {
        return body;
    }

    // Static factory methods for convenience
    public static <T> ResponseEntity<T> ofStatus(int statusCode) {
        return new ResponseEntity<>(statusCode, null);
    }

    public static <T> ResponseEntity<T> of(int statusCode, T body) {
        return new ResponseEntity<>(statusCode, body);
    }
}
