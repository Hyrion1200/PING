package fr.epita.assistants.myide.domain.entity;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Optional;
import java.util.Set;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import fr.epita.assistants.myide.domain.service.Settings;
import org.springframework.stereotype.Service;

public class Project_Entity implements Project {
    // fields
    private Set<Aspect> aspects;
    private Node rootNode;
    public HashMap<String, String> filesContents = new HashMap<String,String>();
    public String ExecResult;

    private Settings settings;
    // constructor
    public Project_Entity(Node rootNode, Set<Aspect> aspects, Settings settings) {
        this.aspects = aspects;
        this.rootNode = rootNode;
        this.settings = settings;
    }

    // methods


    public Settings getSettings() {
        return settings;
    }

    public void setSettings(Settings settings) throws IOException {
        this.settings = settings;
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        FileWriter fw = new FileWriter(rootNode.getPath() + "/.pingsettings");
        String json = ow.writeValueAsString(settings);
        fw.write(json);
        fw.close();
    }

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
