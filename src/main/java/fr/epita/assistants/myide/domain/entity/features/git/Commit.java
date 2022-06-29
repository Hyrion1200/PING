package fr.epita.assistants.myide.domain.entity.features.git;

import fr.epita.assistants.myide.domain.entity.Mandatory;
import fr.epita.assistants.myide.domain.entity.Project;
import fr.epita.assistants.myide.domain.entity.features.exec_report.ExecReport;
import org.eclipse.jgit.api.CommitCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.PersonIdent;

import java.sql.DatabaseMetaData;
import java.sql.Time;
import java.util.Date;
import java.util.TimeZone;

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
            var commit = commitCommand.call();

            PersonIdent commiterIdent = commit.getCommitterIdent();
            var msg = commit.getFullMessage();
            var nb = commit.getId().getName();
            String res = "[commit " + nb + "] - " + msg + "\n";
            res += commiterIdent.getName() + " " + commiterIdent.getEmailAddress() + " " + commiterIdent.getWhen();

            return new ExecReport(ExecReport.Status.SUCCESS, res, "Git commit successful");

        } catch (GitAPIException e) {
            e.printStackTrace();
        }
        return new ExecReport(ExecReport.Status.ERROR, "Git commit failed with message: " + message);
    }
}