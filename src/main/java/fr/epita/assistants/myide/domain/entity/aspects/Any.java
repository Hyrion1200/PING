package fr.epita.assistants.myide.domain.entity.aspects;

import java.util.List;
import javax.validation.constraints.NotNull;
import fr.epita.assistants.myide.domain.entity.Aspect;
import fr.epita.assistants.myide.domain.entity.Feature;

public
class Any extends Aspects {
  public Any() {
    super(Type.ANY);
  }
}
