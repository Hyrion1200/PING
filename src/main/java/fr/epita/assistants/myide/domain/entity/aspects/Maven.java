package fr.epita.assistants.myide.domain.entity.aspects;

import java.util.List;

import javax.validation.constraints.NotNull;

import fr.epita.assistants.myide.domain.entity.Aspect;
import fr.epita.assistants.myide.domain.entity.Feature;
import fr.epita.assistants.myide.domain.entity.features.Features.FeaturesTypes;

public
class Maven extends Aspects {
public
  Maven() { super(Type.MAVEN); }
}
