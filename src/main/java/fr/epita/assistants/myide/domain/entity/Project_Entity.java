package fr.epita.assistants.myide.domain.entity;

import java.util.HashMap;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

public class Project_Entity implements Project {
    // fields
    private Set<Aspect> aspects;
    private Node rootNode;
    public HashMap<String, String> filesContents = new HashMap<String,String>();

    // constructor
    public Project_Entity(Node rootNode, Set<Aspect> aspects) {
        this.aspects = aspects;
        this.rootNode = rootNode;
    }

    // methods

    @Override
    public Node getRootNode() {
        return rootNode;
    }

    @Override
    public Set<Aspect> getAspects() {
        return aspects;
    }

    @Override
    public Optional<Feature> getFeature(Feature.Type featureType) {
        for (var aspect : this.aspects) {
            var feature = aspect.getFeatureList().stream().filter(f-> f.type() == featureType).findFirst();
            if (!feature.isEmpty())
                return feature;
        }

        return Optional.empty();
    }
}
