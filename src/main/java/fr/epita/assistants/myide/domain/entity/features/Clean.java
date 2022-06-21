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