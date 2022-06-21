package fr.epita.assistants.myide.domain.entity.features;

import fr.epita.assistants.myide.domain.entity.Feature.ExecutionReport;
import fr.epita.assistants.myide.domain.entity.Feature;
import fr.epita.assistants.myide.domain.entity.Project;

public
class Clean extends Features {
  public Clean() {
    super(FeaturesTypes.Git.CLEAN);
  }
  @Override public ExecutionReport execute(Project project, Object... params) {
    // Execute mvn clean
    try {
      final int exitCode = Runtime.getRuntime().exec("mvn clean").waitFor();
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
}