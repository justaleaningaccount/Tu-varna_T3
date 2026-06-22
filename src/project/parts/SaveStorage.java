package project.parts;

import project.interfaces.FileManager;
/**
 * Concrete FileManager that delegates load/save to XmlLoader.
 * Provides a simple persistence adapter used by Context and Engine.
 */
public class SaveStorage implements FileManager {
    private final XmlLoader loader;

    public SaveStorage(XmlLoader loader) {
        this.loader = loader;
    }

    @Override
    public Element load(String filename) throws Exception {
        return loader.load(filename);
    }

    @Override
    public void saving(Element element, String filename) throws Exception {
        loader.save(element, filename);
    }
}
