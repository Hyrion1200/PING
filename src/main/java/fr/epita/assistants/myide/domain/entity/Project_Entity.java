package fr.epita.assistants.myide.domain.entity;

import java.util.Optional;
import java.util.Set;

public
class Project_Entity implements Project {
  // constructor
public
  Project_Entity(String path, Node rootNode, Set<Aspect> aspects) {
    this.aspects = aspects;
    this.rootNode = rootNode;
    this.feature = Optional.empty();
  }

  // methods

  @Override public Node getRootNode() {
    return rootNode;
  }

  @Override public Set<Aspect> getAspects() {
    return aspects;
  }

  @Override public Optional<Feature> getFeature(Feature.Type featureType) {
    return feature;
  }

  // fields
private
  Set<Aspect> aspects;
private
  Node rootNode;
private
  Optional<Feature> feature;
}