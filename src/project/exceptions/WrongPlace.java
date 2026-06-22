package project.exceptions;
/**
 * Runtime exception indicating an XML parsing location or structural error.
 * Thrown by XmlParser when encountering unexpected tokens or structure.
 */
public class WrongPlace extends RuntimeException {
    public WrongPlace(String message) {
        super(message);
    }
}
