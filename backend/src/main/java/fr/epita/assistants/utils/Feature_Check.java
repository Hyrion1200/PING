package fr.epita.assistants.utils;

import java.util.Set;

import fr.epita.assistants.myide.domain.entity.Aspect;
import fr.epita.assistants.myide.domain.entity.Mandatory;
import fr.epita.assistants.myide.domain.entity.Project;

public
class Feature_Check {
  Feature_Check(Project project, Mandatory.Aspects type) {
    this.project = project;
    this.type = type;
  }

  boolean check() {
    Set<Aspect> aspects = project.getAspects();
    for (Aspect aspect : aspects) {
      if (aspect.getType() == type) {
        return true;
      }
    }
    return false;
  }

private
  Project project;
  Mandatory.Aspects type;
}
