package Project.Commands;

import Project.Parts.Element;
import Project.Parts.SaveStorage;
/**
 * Application entry point that initializes context, root element and engine.
 * Sets up a minimal in-memory document and starts the interactive Engine loop.
 */
public class Main
{
        public static void main(String[] args)
        {
            CommandExecution commandExecution = new CommandExecution();
            Context context = new Context(new SaveStorage());
            Element root = new Element("root");
            root.id = 1;
            context.setElement(root);
            Engine engine = new Engine(context, commandExecution);
            engine.run();
        }
    }
