package fr.epita.assistants.myide.domain.entity.features.maven;

import fr.epita.assistants.myide.domain.entity.Mandatory;

public class Tree extends Maven_features {
    public Tree() {
        super(Mandatory.Features.Maven.TREE, "mvn dependency:tree");
    }
}
