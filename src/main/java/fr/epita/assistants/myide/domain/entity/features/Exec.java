package fr.epita.assistants.myide.domain.entity.features;

import java.util.Set;

import fr.epita.assistants.myide.domain.entity.Aspect;
import fr.epita.assistants.myide.domain.entity.Feature;
import fr.epita.assistants.myide.domain.entity.Mandatory;
import fr.epita.assistants.myide.domain.entity.Project;
import fr.epita.assistants.utils.Feature_Check;

public
class Exec implements Feature {
  @Override public ExecutionReport execute(Project project, Object... params) {
    // Check if the project has the Maven aspect type
    Feature_Check checker = new Feature_Check(project, Mandatory.Aspects.MAVEN);
    if (checker.check()) {
      // Execute mvn exec
      try {
        final int exitCode =
            Runtime.getRuntime().exec("mvn exec " + params).waitFor();
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