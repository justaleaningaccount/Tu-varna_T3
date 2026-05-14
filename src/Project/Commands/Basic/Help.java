package Project.Commands.Basic;

import Project.Commands.Context;
import Project.Exceptions.WrongCommand;
import Project.Interfaces.Command;
/**
 * Provides a multi-line help text describing available commands.
 * Returns localized/help strings for the interactive console.
 * Used by the engine to show usage information to the user.
 */

public class Help implements Command
{

    @Override
    public String execute(String[] args, Context context) throws WrongCommand {
        return  """
                Commands :help\s
                "Open: Отваря файла със данни."\s
                "Close: Затваря текущия файл."\s
                "Save: Запазва текущия файл"\s
                "Save as : Запазва данните в нов файл"\s
                "Help: Помощ за приспособлението на различните команди"\s
                "Exit: Затваря програмата"\s
                "Print: Извежда на екрана информацията от XML файла"\s
                "Select: Извежда стойност на атрибут по даден идентификатор на елемента и ключ на атрибута"\s
                "Set: Присвоява стойност на атрибут"\s
                "Children: Списък с атрибути на вложените елементи"\s
                "Child: Достъп до н-тия наследник на елемент"\s
                "Text: Достъп до текста на елемент"\s
                "Delete: Изтриване на атрибут на елемент по ключ"
                "Newchild: Добавяне на НОВ наследник на елемент. Новият елемент няма атрибути, освен идетификатор"
               \s""";
    }

    @Override
    public String helpMsg() {
        return "Показва как работят командите";
    }
}
