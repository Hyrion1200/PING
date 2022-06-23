package fr.epita.assistants.myide.domain.entity.features.maven;

import fr.epita.assistants.myide.domain.entity.Mandatory;

public class Exec extends Maven_features {
    public Exec() {
        super(Mandatory.Features.Maven.EXEC, "exec");
    }
}
