package fr.epita.assistants.myide.domain.service;


import java.util.ArrayList;
import java.util.List;

public class Settings {
    public String Theme = null;
    public String Langue = null;
    public ArrayList<KeyBind> KeyBindings = new ArrayList();

    @Override
    public String toString() {
        return String.format("{Theme: %s, " +
                "Language: %s," +
                "KeyBindings: %s}",Theme, Langue, KeyBindings);
    }
}
