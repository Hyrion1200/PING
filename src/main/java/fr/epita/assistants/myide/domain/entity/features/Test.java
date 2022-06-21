package fr.epita.assistants.myide.domain.entity.features;

import fr.epita.assistants.myide.domain.entity.Feature;
import fr.epita.assistants.myide.domain.entity.Feature.ExecutionReport;
import fr.epita.assistants.myide.domain.entity.Project;

public
class Test implements Feature {
  @Override public ExecutionReport execute(Project project, Object... params) {
    // Execute mvn test
    try {
      final int exitCode = Runtime.getRuntime().exec("mvn test").waitFor();
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
    return new ExecutionReport() {
      @Override public boolean isSuccess() {
        return false;
      }
    };
  }

  @Override public Type type() {
    // Are we not supposed to change any @Given file ? If so how do I return a
    // Type.MAVEN ?
    return Type.MAVEN;
  }
}