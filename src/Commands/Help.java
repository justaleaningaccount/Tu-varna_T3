package Commands;

import Exceptions.WrongCommand;

import java.util.List;
import java.util.Map;

public class Help implements Command
{
    private Map<String,Command> commands;

    public Help(Map<String,Command> commands) {
        this.commands = commands;
    }

    @Override
    public String execute(List<String> args) throws WrongCommand {
        String helpMessage = """
                Commands : \n" +
                "Open: Отваря файла със данни. \n" +
                "Close: Затваря текущия файл. \n" +
                "Save: Запазва текущия файл \n" +
                "Save as : Запазва данните в нов файл \n" +
                "Help: Помощ за приспособлението на различните команди \n +" +
                "Exit: Затваря програмата \n" +
                "Print: Извежда на екрана информацията от XML файла \n" +
                "Select: Извежда стойност на атрибут по даден идентификатор на елемента и ключ на атрибута \n" +
                "Set: Присвоява стойност на атрибут \n" +
                "Children: Списък с атрибути на вложените елементи \n" +
                "Child: Достъп до н-тия наследник на елемент \n" +
                "Text: Достъп до текста на елемент \n" +
                "Delete: Изтриване на атрибут на елемент по ключ \n" +
                "Newchild: Добавяне на НОВ наследник на елемент. Новият елемент няма атрибути, освен идетификатор \n" +
                "Xpath: операция за изпълнение на прости XPath 2.0 заявки към даден елемент,която връща списък от XML елементи
                """;
        if (args.isEmpty())
        {
            return helpMessage;
        }
        else
        {
            String commandName = args.getFirst().toLowerCase();
            Command command = commands.get(commandName);
            if (command != null)
            {
                return command.helpMsg();
            }
            else
            {
                throw new WrongCommand("No such command exist: " + commandName);
            }
        }

    }

    @Override
    public String helpMsg() {
        return "Показва как работят командите";
    }
}
