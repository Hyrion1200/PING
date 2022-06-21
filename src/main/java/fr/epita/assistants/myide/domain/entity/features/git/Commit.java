package fr.epita.assistants.myide.domain.entity.features.git;

import fr.epita.assistants.myide.domain.entity.Project;
import fr.epita.assistants.myide.domain.entity.features.exec_report.ExecReport;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;

public
class Commit extends Git_features {
  Commit() {
    super(FeaturesTypes.Git.COMMIT);
  }

  @Override public ExecutionReport execute(Project project, Object... params) {
    // DONE
    StringBuilder args = new StringBuilder();
    for (Object param : params) {
      args.append(param).append(" ");
    }
    Git git = getGit(project);
    try {
      git.commit().setMessage(args.toString()).call();
      return new ExecReport(ExecReport.Status.SUCCESS, "Git commit successful");

    } catch (GitAPIException e) {
      e.printStackTrace();
    }
    return new ExecReport(ExecReport.Status.ERROR, "Git commit failed with message: " + args);
  }
}