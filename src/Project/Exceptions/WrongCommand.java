package Project.Exceptions;
/**
 * Runtime exception indicating an unrecognized or invalid command.
 * Used by CommandExecution when command lookup fails.
 */
public class WrongCommand extends RuntimeException {
    public WrongCommand(String message) {
        super(message);
    }
}
