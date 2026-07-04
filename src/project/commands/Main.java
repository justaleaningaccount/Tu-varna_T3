package project.commands;

import project.parts.Element;
import project.parts.SaveStorage;
import project.parts.XmlLoader;

/**
 * Application entry point that initializes context, root element and engine.
 * Sets up a minimal in-memory document and starts the interactive Engine loop.
 */
public class Main
{
        static void main()
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
