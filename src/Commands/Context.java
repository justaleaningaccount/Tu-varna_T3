package Commands;

import Parts.Element;
import Parts.SaveStorage;

import java.util.Scanner;

public class Context
{
    private Element element;
    private String filename;
    private SaveStorage saveStorage;
    private Scanner scanner;

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

    public Scanner getScanner() {
        return scanner;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }
}
