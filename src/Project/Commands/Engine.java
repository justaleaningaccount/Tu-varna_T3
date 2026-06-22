package Project.Commands;

import Project.Parts.SaveStorage;
import Project.Parts.XmlLoader;

import java.util.Scanner;
import java.util.Arrays;
/**
 * Interactive command loop that reads user input and dispatches commands.
 * Parses simple commands and arguments, handles 'save as' specially.
 * Manages application lifecycle and prints results or error messages.
 */
public class Engine {
    private final Context context;
    private final CommandExecution commandExecution;

    public Engine(Context context, CommandExecution commandExecution) {
        this.context = context;
        this.commandExecution = commandExecution;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        this.context.setScanner(scanner);
        System.out.println("Welcome!Please enter command: \n" +
                "Open. \n" +
                "Closing. \n" +
                "Save. \n" +
                "SavingAs. \n" +
                "Help. \n" +
                "Exit. \n" +
                "Printing. \n" +
                "Select. \n" +
                "Setting. \n" +
                "Children. \n" +
                "Child. \n" +
                "Text. \n" +
                "Delete. \n" +
                "Newchild");

        while (true) {
            System.out.print("> ");
            String input;
            try {
                if (!scanner.hasNextLine()) break;
                input = scanner.nextLine().trim();
            } catch (Exception e) {
                break;
            }

            if (input.isEmpty()) continue;
            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Exiting...");
                break;
            }

            try {
                String commandName;
                String[] args;

                if (input.toLowerCase().startsWith("saving as")) {
                    commandName = "savingAs";
                    String rest = input.substring(9).trim(); // skip "saving as"
                    args = rest.isEmpty() ? new String[0] : new String[] { rest.replace("\"", "") };
                }
                 else {
                    String[] parts = input.split("\\s+");
                    commandName = parts[0];
                    args = parts.length > 1 ? Arrays.copyOfRange(parts, 1, parts.length) : new String[0];
                }

                var command = commandExecution.getCommand(commandName);
                String result = command.execute(args, context);
                if (result != null && !result.isEmpty()) System.out.println(result);

            } catch (Exception e) {
                System.out.println("Invalid command: " + e.getMessage());
            }
        }

        scanner.close();
    }

    public static void main(String[] args) {
        XmlLoader loader = new XmlLoader();
        SaveStorage storage = new SaveStorage(loader);
        Context ctx = new Context(storage);
        CommandExecution exec = new CommandExecution();
        Engine engine = new Engine(ctx, exec);

        Project.Parts.Element root = new Project.Parts.Element("root");
        root.id = 1;
        ctx.setElement(root);

        engine.run();
    }
}
