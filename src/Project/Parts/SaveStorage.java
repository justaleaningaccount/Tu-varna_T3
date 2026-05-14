package Project.Parts;

import Project.Interfaces.FileManager;
/**
 * Concrete FileManager that delegates load/save to XmlLoader.
 * Provides a simple persistence adapter used by Context and Engine.
 */
public class SaveStorage implements FileManager {
    @Override
    public Element load(String filename) throws Exception {
        return XmlLoader.load(filename);
    }

    @Override
    public void saving(Element element, String filename) throws Exception
    {
        XmlLoader.save(element,filename);
    }
}
