package Parts;

import Interfaces.FileManager;

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
