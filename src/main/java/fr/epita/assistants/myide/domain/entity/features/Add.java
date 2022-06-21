package fr.epita.assistants.myide.domain.entity.features;

import fr.epita.assistants.myide.domain.entity.Feature.ExecutionReport;
import fr.epita.assistants.myide.domain.entity.Feature;
import fr.epita.assistants.myide.domain.entity.Project;

public
class Add implements Feature {
  @Override public ExecutionReport execute(Project project, Object... params) {
    // TODO Auto-generated method stub
  }

  class ExecutionReporter implements ExecutionReport {
  public
    boolean isSuccess() { return false; }
  }

  @Override public Type
  type() {
    // Are we not supposed to change any @Given file ? If so how do I return a
    // Type.GIT ?
    return Type.GIT;
  }
}