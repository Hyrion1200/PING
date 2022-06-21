package fr.epita.assistants.myide.domain.entity.features;

import fr.epita.assistants.myide.domain.entity.Feature;
import fr.epita.assistants.myide.domain.entity.Project;

public
class Dist extends Features {
  // - Constructor
  public Dist() {
    super(FeaturesTypes.Any.DIST);
  }

  // - Execute function implementation
  @Override public ExecutionReport execute(Project project, Object... params) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Not supported yet."); // To
                                                                   // change
                                                                   // body
                                                                   // of
                                                                   // generated
                                                                   // methods,
                                                                   // choose
  }
}
