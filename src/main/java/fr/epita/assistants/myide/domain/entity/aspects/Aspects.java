package fr.epita.assistants.myide.domain.entity.aspects;

import fr.epita.assistants.myide.domain.entity.Aspect;
import fr.epita.assistants.myide.domain.entity.Feature;

import java.util.ArrayList;
import java.util.List;

// TODO
// Classe mere pour les aspect
public abstract class Aspects implements Aspect {
    private Type type;
    private List<Feature> features;

    public Aspects(Type type) {
        this.type = type;
        this.features = new ArrayList<Feature>();
    }

    public enum Type implements Aspect.Type {
        ANY,
        GIT,
        MAVEN,
    }

    @Override
    public Aspect.Type getType() {
        return type;
    }

    @Override
    public List<Feature> getFeatureList() {
        return features;
    }
}
