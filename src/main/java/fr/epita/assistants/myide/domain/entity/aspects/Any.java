package fr.epita.assistants.myide.domain.entity.aspects;

import java.util.List;
import javax.validation.constraints.NotNull;
import fr.epita.assistants.myide.domain.entity.Aspect;
import fr.epita.assistants.myide.domain.entity.Feature;

public
class Any implements Aspect {
  @Override public Type getType() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Not supported yet."); // To
                                                                   // change
                                                                   // body
                                                                   // of
                                                                   // generated
                                                                   // methods,
                                                                   // choose
  }

  @Override public @NotNull List<Feature> getFeatureList() {
    // TODO Return all feature from the Any aspect
    // For now return an empty list
    return Aspect.super.getFeatureList();
  }
}
