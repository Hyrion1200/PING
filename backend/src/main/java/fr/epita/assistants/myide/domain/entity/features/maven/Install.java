package fr.epita.assistants.myide.domain.entity.features.maven;

import fr.epita.assistants.myide.domain.entity.Mandatory;

public class Install extends Maven_features {
    public Install() {
        super(Mandatory.Features.Maven.INSTALL, "install");
    }
}
