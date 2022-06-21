package fr.epita.assistants.myide.domain.entity.features;

import fr.epita.assistants.myide.domain.entity.Feature;
import fr.epita.assistants.myide.domain.entity.Project;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;

import java.io.File;
import java.io.IOException;

public
class Git_features implements Feature {
    @Override public Feature.ExecutionReport execute(Project project, Object... params) {
        return null;
    }

    @Override
    public Type type() {
        return null;
    }

    public Git getGit(Project project) {
        Repository repo = null;
        try {
            repo = new FileRepositoryBuilder()
                    .setGitDir(new File(project.getRootNode() + "/.git"))
                    .readEnvironment()
                    .findGitDir()
                    .setMustExist(true)
                    .build();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return new Git(repo);
    }
}

