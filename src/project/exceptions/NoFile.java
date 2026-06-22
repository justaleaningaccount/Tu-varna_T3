package project.exceptions;
/**
 * Runtime exception indicating no file is currently open or available.
 * Thrown by save/close operations when filename/context is missing.
 */
public class NoFile extends RuntimeException {
    public NoFile(String message) {
        super(message);
    }
}
