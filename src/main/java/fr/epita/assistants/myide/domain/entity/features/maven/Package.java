package fr.epita.assistants.myide.domain.entity.features.maven;

import fr.epita.assistants.myide.domain.entity.Mandatory;

public class Package extends Maven_features {
    public Package() {
        super(Mandatory.Features.Maven.PACKAGE, "package");
    }
}
