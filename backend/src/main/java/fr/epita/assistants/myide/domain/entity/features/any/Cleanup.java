package fr.epita.assistants.myide.domain.entity.features.any;

import fr.epita.assistants.myide.domain.entity.Mandatory;
import fr.epita.assistants.myide.domain.entity.Node;
import fr.epita.assistants.myide.domain.entity.Project;
import fr.epita.assistants.myide.domain.entity.features.Features;
import fr.epita.assistants.myide.domain.entity.features.exec_report.ExecReport;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Cleanup extends Features {
    // - Constructor
    public Cleanup() {
        super(Mandatory.Features.Any.CLEANUP);
    }

    // - Private methods
    private List<String> getIgnoredFiles(Node rootNode) throws IOException {
        Path file = Paths.get(rootNode.getPath().toString() + "/.myideignore");

        return Files.readAllLines(file);
    }

    private boolean deletePath(Path path) {
        File file = new File(path.toString());
        if (file.isDirectory()) {
            for (File subFile : file.listFiles()) {
                deletePath(Paths.get(subFile.getPath()));
            }
        }

        return file.delete();
    }

    // - Execute method implementation
    @Override
    public ExecutionReport execute(Project project, Object... params) {
        List<String> ignoredFiles = null;
        List<Path> paths = null;

        // - Get the ignored files
        try {
            ignoredFiles = getIgnoredFiles(project.getRootNode());
        } catch (IOException e) {
            return new ExecReport(ExecReport.Status.ERROR, "Configuration file '.myideignore' not found");
        }

        // - Get all the paths
        try {
            paths = Files.walk(project.getRootNode().getPath()).toList();
        } catch (IOException e) {
            return new ExecReport(ExecReport.Status.ERROR, "Couldn't open the root directory");
        }

        // - Check if the paths are in the ignoredFiles and delete them if needed
        for (Path path : paths) {
            if (ignoredFiles.contains(path.getFileName().toString()) && Files.exists(path)) {
                if (!deletePath(path))
                    return new ExecReport(ExecReport.Status.ERROR, "Couldn't delete trash file: " + path.toString());
            }
        }

        return new ExecReport(ExecReport.Status.SUCCESS);
    }
}
