package fr.epita.assistants.myide.domain.entity.features.maven;

import fr.epita.assistants.myide.domain.entity.Mandatory;

public class Compile extends Maven_features {
    public Compile() {
        super(Mandatory.Features.Maven.COMPILE, "compile");
    }
}
