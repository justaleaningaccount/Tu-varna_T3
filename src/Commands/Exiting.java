package Commands;

import java.util.List;
import java.util.Scanner;

public class Exiting implements Command
{
    public Exiting(){};

    @Override
    public String execute(List<String> args) {
        return "Exit";
    }

    @Override
    public String helpMsg() {
        return "Exits program.\n";
    }
}
