package fr.epita.assistants.myide.domain.entity.features;

import fr.epita.assistants.myide.domain.entity.Node;
import fr.epita.assistants.myide.domain.entity.Project;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Cleanup extends Features {
	// - Constructor
	public Cleanup() {
		super(FeaturesTypes.Any.CLEANUP);
	}

	// - Private methods
	private List<String> getIgnoredFiles(Node rootNode) throws IOException {
		Path file = Paths.get(rootNode.getPath() + "/.myideignore");

		return Files.readAllLines(file);
	}

	// - Execute method implementation
	@Override public ExecutionReport execute(Project project, Object... params) {
		List<String> ignoreFiles = null;

		try {
			ignoredFiles = getIgnoredFiles(project.getRootNode());
		} catch (IOException e) {
			return new Exceptions.
		}
	}
}
