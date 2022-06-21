package fr.epita.assistants.myide.domain.entity.features;

import java.util.Set;

import fr.epita.assistants.myide.domain.entity.Aspect;
import fr.epita.assistants.myide.domain.entity.Feature;
import fr.epita.assistants.myide.domain.entity.Mandatory;
import fr.epita.assistants.myide.domain.entity.Project;

public
class Exec implements Feature {
  @Override public ExecutionReport execute(Project project, Object... params) {
    // Check if the project has the Maven aspect type
    Set<Aspect> aspects = project.getAspects();
    for (Aspect aspect : aspects) {
      if (aspect.getType() == Mandatory.Aspects.MAVEN) {
        // Execute mvn exec with the given parameters
        try {
          final int exitCode = Runtime.getRuntime().exec("mvn exec").waitFor();
          return new ExecutionReport() {
            @Override public boolean isSuccess() {
              return exitCode == 0;
            }
          };
        } catch (InterruptedException e) {
          e.printStackTrace();
        } catch (java.io.IOException e) {
          e.printStackTrace();
        }
      }
    }
    return new ExecutionReport() {
      @Override public boolean isSuccess() {
        return false;
      }
    };
  }

  @Override public Type type() {
    // Are we not supposed to change any @Given file ? If so how do I return a
    // Type.MAVEN ?
    return Mandatory.Features.Maven.EXEC;
  }
}