package Interfaces;

import Parts.Element;

public interface FileManager
{
    Element load(String filename) throws Exception;
    void saving(Element element,String filename) throws Exception;
}
