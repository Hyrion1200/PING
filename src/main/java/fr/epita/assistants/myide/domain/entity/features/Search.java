package fr.epita.assistants.myide.domain.entity.features;

import fr.epita.assistants.myide.domain.entity.Feature.ExecutionReport;
import fr.epita.assistants.myide.domain.entity.Feature;
import fr.epita.assistants.myide.domain.entity.Project;
import fr.epita.assistants.myide.domain.entity.features.exec_report.ExecReport;

public
class Search extends Features {
	// - Constructor
	public Search() {
		super(FeaturesTypes.Any.SEARCH);
	}

	// - Execute method implementation
	@Override public ExecutionReport execute(Project project, Object... params) {
		return new ExecReport(ExecReport.Status.SUCCESS);
	}
}
