package fr.epita.assistants.myide.domain.entity.features.git;

import fr.epita.assistants.myide.domain.entity.Mandatory;
import fr.epita.assistants.myide.domain.entity.Project;
import fr.epita.assistants.myide.domain.entity.features.exec_report.ExecReport;
import org.eclipse.jgit.api.CommitCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;

public
class Commit extends Git_features {
    public Commit() {
        super(Mandatory.Features.Git.COMMIT);
    }

    @Override public ExecutionReport execute(Project project, Object... params) {
        // DONE
        String message = "";
        if (params.length >= 1)
            message = params[0].toString();

        Git git = getGit(project);
        try {
            CommitCommand commitCommand = git.commit();
            commitCommand.setMessage(message);
            commitCommand.call();
            return new ExecReport(ExecReport.Status.SUCCESS, "Git commit successful");

        } catch (GitAPIException e) {
            e.printStackTrace();
        }
        return new ExecReport(ExecReport.Status.ERROR, "Git commit failed with message: " + message);
    }
}