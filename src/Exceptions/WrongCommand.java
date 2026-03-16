package Exceptions;

public class WrongCommand extends RuntimeException {
    public WrongCommand(String message) {
        super(message);
    }
}
