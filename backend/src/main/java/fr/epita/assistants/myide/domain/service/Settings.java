package fr.epita.assistants.myide.domain.service;

import java.util.ArrayList;
import java.util.List;

public class Settings {
    public String Theme = null;
    public String Langue = null;
    public Boolean blindMode = true;
    public Boolean musicMode = false;
    public ArrayList<KeyBind> KeyBindings = new ArrayList();

    @Override
    public String toString() {
        return String.format("{Theme: %s, " +
                "Language: %s," +
                "KeyBindings: %s," +
                "blindMode: %s," +
                "musicMode: %s}", Theme, Langue, KeyBindings, blindMode, musicMode);
    }

    public Object getSetting(String key) {
        // TODO we can improve this by iterating to be case insensitive
        try {
            return this.getClass().getField(key).get(this);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            return null;
        }
    }
}
