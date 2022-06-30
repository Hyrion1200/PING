package fr.epita.assistants.myide.domain.entity.aspects;

import java.util.ArrayList;

import edu.emory.mathcs.backport.java.util.Arrays;
import fr.epita.assistants.myide.domain.entity.Feature;
import fr.epita.assistants.myide.domain.entity.Mandatory;
import fr.epita.assistants.myide.domain.entity.features.git.*;

public
class Git extends Aspects {
    public Git() {
        super(Mandatory.Aspects.GIT);

        // TODO create the GIT object once here and provide it to the features
        this.features.add(new Add());
        this.features.add(new Commit());
        this.features.add(new Pull());
        this.features.add(new Push());
    }
}
