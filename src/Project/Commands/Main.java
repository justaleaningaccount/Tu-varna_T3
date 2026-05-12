package Project.Commands;

import Project.Parts.Element;
import Project.Parts.SaveStorage;

public class Main {
    public static void main(String[] args) {
        CommandExecution commandExecution = new CommandExecution();
        Context context = new Context(new SaveStorage());

        Element root = new Element("root");
        root.id.put(0, 1);
        context.setElement(root);

        Engine engine = new Engine(context, commandExecution);
        engine.run();
    }
}
