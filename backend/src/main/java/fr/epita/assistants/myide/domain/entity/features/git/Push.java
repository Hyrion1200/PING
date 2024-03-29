package fr.epita.assistants.myide.domain.entity.features.git;

import fr.epita.assistants.myide.domain.entity.Mandatory;
import fr.epita.assistants.myide.domain.entity.Project;
import fr.epita.assistants.myide.domain.entity.features.exec_report.ExecReport;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.PushCommand;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;

public
class Push extends Git_features {
    public Push() {
        super(Mandatory.Features.Git.PUSH);
    }

    @Override public ExecutionReport execute(Project project, Object... params) {
        // DONE

        Git git = getGit(project);
        String user = (String) params[0];
        String password = (String) params[1];
        try {
            PushCommand pushCommand = git.push();
            pushCommand.setRemote("origin");
            pushCommand.setCredentialsProvider(new UsernamePasswordCredentialsProvider(user, password));
            pushCommand.call();
            String res = "";
            res += pushCommand.getRemote();
            return new ExecReport(ExecReport.Status.SUCCESS, String.format("Pushed to %s.", res), "Git push successful");

        } catch (GitAPIException e) {
            e.printStackTrace();
        }
        return new ExecReport(ExecReport.Status.ERROR, "Git push failed");
    }
}