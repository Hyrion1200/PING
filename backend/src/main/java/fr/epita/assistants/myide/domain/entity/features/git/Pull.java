package fr.epita.assistants.myide.domain.entity.features.git;

import fr.epita.assistants.myide.domain.entity.Mandatory;
import fr.epita.assistants.myide.domain.entity.Project;
import fr.epita.assistants.myide.domain.entity.features.exec_report.ExecReport;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.MergeCommand;
import org.eclipse.jgit.api.PullCommand;
import org.eclipse.jgit.api.errors.GitAPIException;

public
class Pull extends Git_features {
    public Pull() {
        super(Mandatory.Features.Git.PULL);
    }

    @Override public ExecutionReport execute(Project project, Object... params) {
        // DONE
        System.out.println("Pull");

        Git git = getGit(project);
        try {
            PullCommand pullCommand = git.pull();
            pullCommand.setFastForward(MergeCommand.FastForwardMode.FF);
            var pull = pullCommand.call();
            if (!pull.isSuccessful())
                return new ExecReport(ExecReport.Status.ERROR, String.format("Cannot pull from git '%s'", git.getRepository().toString()));
            String res = pull.getMergeResult().getMergeStatus().toString();
            return new ExecReport(ExecReport.Status.SUCCESS, res, "Git pull successful");

        } catch (GitAPIException e) {
            e.printStackTrace();
        }
        return new ExecReport(ExecReport.Status.ERROR, "Git pull failed");
    }
}