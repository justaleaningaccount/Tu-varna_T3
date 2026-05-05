package Exceptions;

public class WrongPlace extends RuntimeException {
    public WrongPlace(String message) {
        super(message);
    }
}
