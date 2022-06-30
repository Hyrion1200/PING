package fr.epita.assistants.myide.domain.entity;

import java.nio.file.Path;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

public class Node_Entity implements Node {
  // Constructor
public
  Node_Entity(Path path, Node.Type type, List<@NotNull Node> children) {
    this.path = path;
    this.type = type;
    this.children = children;
  }

  // Methods
  public void setPath(Path path) {
    this.path = path;
  }

  @Override public @NotNull Path getPath() {
    return path;
  }

  @Override public @NotNull Type getType() {
    return type;
  }

  @Override public @NotNull List<@NotNull Node> getChildren() {
    return children;
  }

  public @NotNull void addChildren(Node child) {
    children.add(child);
  }
  // Fields

@Autowired
private Path path;

@Autowired
private Node.Type type;

@Autowired
private List<@NotNull Node> children;
}
