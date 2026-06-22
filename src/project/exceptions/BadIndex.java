package project.exceptions;
/**
 * Runtime exception indicating an invalid numeric index or id.
 * Thrown when parsing or validating element/child indices fails.
 */
public class BadIndex extends RuntimeException {
    public BadIndex(String message) {
        super(message);
    }
}
