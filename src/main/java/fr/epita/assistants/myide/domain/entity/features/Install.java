package fr.epita.assistants.myide.domain.entity.features;

import fr.epita.assistants.myide.domain.entity.Feature;
import fr.epita.assistants.myide.domain.entity.Feature.ExecutionReport;
import fr.epita.assistants.myide.domain.entity.Project;

public
class Install implements Feature {
  @Override public ExecutionReport execute(Project project, Object... params) {
    // Execute mvn install
    try {
      final int exitCode = Runtime.getRuntime().exec("mvn install").waitFor();
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