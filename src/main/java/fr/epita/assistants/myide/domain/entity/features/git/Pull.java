package fr.epita.assistants.myide.domain.entity.features.git;

import fr.epita.assistants.myide.domain.entity.Project;
import fr.epita.assistants.myide.domain.entity.features.exec_report.ExecReport;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;

public
class Pull extends Git_features {
  Pull() {
    super(FeaturesTypes.Git.PULL);
  }

  @Override public ExecutionReport execute(Project project, Object... params) {
    // DONE

    Git git = getGit(project);
    try {
      git.pull().call();
      return new ExecReport(ExecReport.Status.SUCCESS, "Git pull successful");

    } catch (GitAPIException e) {
      e.printStackTrace();
    }
    return new ExecReport(ExecReport.Status.ERROR, "Git pull failed");
  }
}