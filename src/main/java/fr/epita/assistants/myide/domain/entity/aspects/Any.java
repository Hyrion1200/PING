package fr.epita.assistants.myide.domain.entity.aspects;

import fr.epita.assistants.myide.domain.entity.Mandatory;
import fr.epita.assistants.myide.domain.entity.Mandatory.Features.Any.*;
import fr.epita.assistants.myide.domain.entity.features.any.Cleanup;
import fr.epita.assistants.myide.domain.entity.features.any.Dist;
import fr.epita.assistants.myide.domain.entity.features.any.Open;
import fr.epita.assistants.myide.domain.entity.features.any.Search;

public class Any extends Aspects {
    public Any() {
        super(Mandatory.Aspects.ANY);

        this.features.add(new Cleanup());
        this.features.add(new Open());
        this.features.add(new Dist());
        this.features.add(new Search());
    }
}
