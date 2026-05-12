package Project.Commands;

import Project.Parts.Element;
import Project.Parts.SaveStorage;

import java.util.Scanner;


public class Context
{
    private Element element;
    private String filename;
    private SaveStorage saveStorage;

    public Context(SaveStorage saveStorage) {
        this.element = null;
        this.filename = null;
        this.saveStorage = saveStorage;
    }

    public Element getElement() {
        return element;
    }

    public void setElement(Element element) {
        this.element = element;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public SaveStorage getSaveStorage() {
        return saveStorage;
    }

    public void setSaveStorage(SaveStorage saveStorage) {
        this.saveStorage = saveStorage;
    }
    public void setScanner(Scanner scanner)
    {

    }
    public boolean hasOpenFile() {
        return filename != null && !filename.isEmpty();
    }


}
