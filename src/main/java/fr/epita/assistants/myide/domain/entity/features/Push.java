package fr.epita.assistants.myide.domain.entity.features;

import fr.epita.assistants.myide.domain.entity.Feature;
import fr.epita.assistants.myide.domain.entity.Feature.ExecutionReport;
import fr.epita.assistants.myide.domain.entity.Project;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;

import java.io.File;
import java.io.IOException;

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
      return new ExecutionReport() {
        @Override public boolean isSuccess() {
          return true;
        }
      };

    } catch (GitAPIException e) {
      e.printStackTrace();
    }
    return new ExecutionReport() {
      @Override public boolean isSuccess() {
        return false;
      }
    };
  }
}