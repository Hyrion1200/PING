package fr.epita.assistants.myide.domain.entity.features;

import fr.epita.assistants.myide.domain.entity.Feature.ExecutionReport;
import fr.epita.assistants.myide.domain.entity.Feature;
import fr.epita.assistants.myide.domain.entity.Project;

public
class Search implements Feature {
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

  @Override public Type type() {
    // Are we not supposed to change any @Given file ? If so how do I return a
    // Type.Any ?
    return Type.Any;
  }
}
