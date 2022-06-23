package fr.epita.assistants.myide.domain.entity.features.maven;

import fr.epita.assistants.myide.domain.entity.Mandatory;

public class Clean extends Maven_features {
    public Clean() {
        super(Mandatory.Features.Maven.CLEAN, "clean");
    }
}
