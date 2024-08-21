package be.kyanvde.model;

/**
 * @author kyanv
 * @file CoordType.java
 * @description
 * @created 21/08/2024
 */

public enum CoordType {
    STRING_ARRAY_REVERSE("stringarrayreverse");

    private final String lowercase;

    CoordType(String lowercase) {
        this.lowercase = lowercase;
    }

    public String getLowercase() {
        return lowercase;
    }
}
