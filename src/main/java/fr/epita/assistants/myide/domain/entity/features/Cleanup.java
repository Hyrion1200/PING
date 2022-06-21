package fr.epita.assistants.myide.domain.entity.features;

import fr.epita.assistants.myide.domain.entity.Feature;
import fr.epita.assistants.myide.domain.entity.Project;

public class Cleanup extends Features {
  public Cleanup() {
    super(FeaturesTypes.Any.CLEANUP);
  }

  @Override public ExecutionReport execute(Project project, Object... params) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException(
        "Not supported yet."); // To change body of generated methods, choose
                               // Tools | Templates.
  }
}
