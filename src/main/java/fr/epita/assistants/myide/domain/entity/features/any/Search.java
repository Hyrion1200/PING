package fr.epita.assistants.myide.domain.entity.features.any;

import fr.epita.assistants.myide.domain.entity.Feature.ExecutionReport;
import fr.epita.assistants.myide.domain.entity.Feature;
import fr.epita.assistants.myide.domain.entity.Mandatory;
import fr.epita.assistants.myide.domain.entity.Project;
import fr.epita.assistants.myide.domain.entity.features.Features;
import fr.epita.assistants.myide.domain.entity.features.exec_report.ExecReport;

public class Search extends Features {
    // - Constructor
    public Search() {
        super(Mandatory.Features.Any.SEARCH);
    }

    // - Execute method implementation
    @Override
    public ExecutionReport execute(Project project, Object... params) {
        // TODO
        return new ExecReport(ExecReport.Status.SUCCESS);
    }
}
