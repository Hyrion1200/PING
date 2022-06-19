package fr.epita.assistants.myide.domain.entity.features;

import fr.epita.assistants.myide.domain.entity.Feature.ExecutionReport;

import fr.epita.assistants.myide.domain.entity.Feature;
import fr.epita.assistants.myide.domain.entity.Project;

public
class Commit implements Feature {
  @Override public ExecutionReport execute(Project project, Object... params) {
    // DONE
    ProcessBuilder pb = new ProcessBuilder()
                        .command("git", "commit", "-m", "\"" + (String) params[0] + "\"")
                        .directory(project.getRootNode().getPath().toFile());

    try {
      Process p = pb.start();
      int exit = p.waitFor();

      if (exit != 0) {
        throw new Exception(String.format("git commit returned %d", exit));
      }
      return new ExecutionReport("test", exit);
      // implement ExecutionReport ?
    } 
    catch (Exception  e) {
      e.printStackTrace();
      return new ExecutionReport("test", -1);
      // implement ExecutionReport ?
    }
    throw new UnsupportedOperationException("Not supported yet."); // To
  }

  @Override public Type type() {
    // Are we not supposed to change any @Given file ? If so how do I return a
    // Type.GIT ?
    return Type.GIT;
  }
}