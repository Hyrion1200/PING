package fr.epita.assistants.myide.domain.service;

public class KeyBind {

    public String Key;
    public String Bind;

    @Override
    public String toString() {
        return String.format("{%s: %s}",Key,Bind);
    }
}
