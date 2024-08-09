package be.kyanvde.exception;

/**
 * @author kyanv
 * @file BTEWebsiteAPIException.java
 * @description A custom exception to enable better error handling.
 * @created 9/08/2024
 */

public class BTEWebsiteAPIException extends RuntimeException {

    public BTEWebsiteAPIException(String message) {
        super(message);
    }
}
