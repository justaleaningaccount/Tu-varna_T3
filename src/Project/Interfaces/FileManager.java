package Project.Interfaces;

import Project.Parts.Element;
/**
 * Abstraction for file operations used by the application.
 * Implementations must provide load and saving methods for Element trees.
 * Allows swapping persistence strategies (e.g., file, memory, test doubles).
 */
public interface FileManager
{
    Element load(String filename) throws Exception;
    void saving(Element element,String filename) throws Exception;
}
