package fr.epita.assistants.myide.domain.entity;

import java.nio.file.Path;
import java.util.List;

import javax.validation.constraints.NotNull;

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
private
  Path path;
private
  Node.Type type;
private
  List<@NotNull Node> children;
}
