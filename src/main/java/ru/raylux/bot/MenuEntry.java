package ru.raylux.bot;

public class MenuEntry {
    private String name;

    public MenuEntry(String name) {
        setName(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
