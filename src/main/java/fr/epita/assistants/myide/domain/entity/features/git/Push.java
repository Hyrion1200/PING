package fr.epita.assistants.myide.domain.entity.features.git;

import fr.epita.assistants.myide.domain.entity.Project;
import fr.epita.assistants.myide.domain.entity.features.exec_report.ExecReport;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;

public
class Push extends Git_features {
  Push() {
    super(FeaturesTypes.Git.PUSH);
  }

  @Override public ExecutionReport execute(Project project, Object... params) {
    // DONE

    Git git = getGit(project);
    try {
      git.push().call();
      return new ExecReport(ExecReport.Status.SUCCESS, "Git push successful");

    } catch (GitAPIException e) {
      e.printStackTrace();
    }
    return new ExecReport(ExecReport.Status.ERROR, "Git push failed");
  }
}