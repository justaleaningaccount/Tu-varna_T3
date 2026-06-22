package Project.Commands;

import Project.Parts.Element;
import Project.Parts.SaveStorage;
import Project.Parts.XmlLoader;

/**
 * Application entry point that initializes context, root element and engine.
 * Sets up a minimal in-memory document and starts the interactive Engine loop.
 */
public class Main
{
        public static void main(String[] args)
        {
            XmlLoader loader = new XmlLoader();
            SaveStorage storage = new SaveStorage(loader);
            Context ctx = new Context(storage);
            CommandExecution exec = new CommandExecution();
            Engine engine = new Engine(ctx, exec);
            Element root = new Element("root");
            root.id = 1;
            ctx.setElement(root);
            engine.run();
        }
    }
