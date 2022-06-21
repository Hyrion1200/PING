package fr.epita.assistants.myide.domain.entity.features;

import fr.epita.assistants.myide.domain.entity.Feature.ExecutionReport;
import fr.epita.assistants.myide.domain.entity.Feature;
import fr.epita.assistants.myide.domain.entity.Project;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

public
class Add extends Git_features {

  Add() {
    super(FeaturesTypes.Git.ADD);
  }

  @Override public ExecutionReport execute(Project project, Object... params) {
    StringBuilder args = new StringBuilder();
    for (Object param : params) {
      args.append(param).append(" ");
    }

    Git git = getGit(project);
    try {
      for (String arg : args.toString().split(" ")) {
        git.add().addFilepattern(arg).call();
      }

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