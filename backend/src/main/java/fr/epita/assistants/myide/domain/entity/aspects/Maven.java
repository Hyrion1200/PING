package fr.epita.assistants.myide.domain.entity.aspects;

import fr.epita.assistants.myide.domain.entity.Mandatory;
import fr.epita.assistants.myide.domain.entity.features.maven.*;
import fr.epita.assistants.myide.domain.entity.features.maven.Package;

public class Maven extends Aspects {
    public Maven() {
        super(Mandatory.Aspects.MAVEN);

        this.features.add(new Clean());
        this.features.add(new Compile());
        this.features.add(new Exec());
        this.features.add(new Install());
        this.features.add(new Package());
        this.features.add(new Test());
        this.features.add(new Tree());
    }
}
