package ru.raylux.bot;

import java.util.ArrayList;
import java.util.List;

public class Menu {
    private List<String> commands = new ArrayList<>();

    public Menu() {
        commands.add("R-lux");
        commands.add("A-lux");
        commands.add("C-lux");
        commands.add("H-lux");
        commands.add("i-lux");
        commands.add("P-lux");
        commands.add("O-lux");
        commands.add("V-lux");
        commands.add("S-lux");
        commands.add("Нестандарт");
    }

    public List<String> getCommands() {
        return commands;
    }

    public void setCommands(List<String> commands) {
        this.commands = commands;
    }

    public String searchCommand(String command) {
        if (commands.contains(command)) {
            return command;
        } else {
            return "Такой команды не существует";
        }
    }
}
