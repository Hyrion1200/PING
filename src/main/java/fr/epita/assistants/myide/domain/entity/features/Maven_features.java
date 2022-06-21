package fr.epita.assistants.myide.domain.entity.features;

import fr.epita.assistants.myide.domain.entity.Feature;
import fr.epita.assistants.myide.domain.entity.Project;

public
abstract class Maven_features extends Features {
private
  String name;

public
  Maven_features(Feature.Type type, String str) {
    super(type);
    this.name = str;
  }

  Maven_features(Feature.Type type) { super(type); }

public
  ExecutionReport execute(Project project, Object... params) {
    // Execute mvn clean
    StringBuilder command = new StringBuilder(name);
    for (Object object : params) {
      command.append(object.toString());
    }
    try {
      final int exitCode =
          Runtime.getRuntime().exec(command.toString()).waitFor();
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
