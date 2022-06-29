package fr.epita.assistants.myide.domain.entity.features.maven;

import fr.epita.assistants.myide.domain.entity.Mandatory;

public class Test extends Maven_features {
    public Test() {
        super(Mandatory.Features.Maven.TEST, "test");
    }
}
