package project.exceptions;
/**
 * Runtime exception indicating a requested element was not found.
 * Thrown by commands and parser when an element lookup fails.
 */
public class NoElement extends RuntimeException {
    public NoElement(String message) {
        super(message);
    }
}
