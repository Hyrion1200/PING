package fr.epita.assistants.myide.domain.entity.features.any;

import fr.epita.assistants.myide.domain.entity.Feature;
import fr.epita.assistants.myide.domain.entity.Mandatory;
import fr.epita.assistants.myide.domain.entity.Node;
import fr.epita.assistants.myide.domain.entity.Project;
import fr.epita.assistants.myide.domain.entity.features.Features;
import fr.epita.assistants.myide.domain.entity.features.exec_report.ExecReport;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.apache.commons.compress.utils.FileNameUtils;
import org.apache.commons.compress.utils.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

public class Dist extends Features {
    // - Constructor
    public Dist() {
        super(Mandatory.Features.Any.DIST);
    }

    private Path treatPath(Path path) {
        String baseName = FileNameUtils.getBaseName(path.toString());

        if (baseName.equals(".") || baseName.equals("./")) {
            Path treatedPath = Paths.get("");

            if (path.isAbsolute())
                treatedPath = treatedPath.toAbsolutePath();

            return treatedPath;
        }

        return path;
    }

    // - Private methods
    private Path getEntryPath(String rootName, File file) {
        if (rootName.equals(".") || rootName.equals("./"))
            return Paths.get(rootName);

        List<String> filePathSplitted = Arrays.stream(file.getPath().split(rootName)).toList();
        return Paths.get(rootName + "/" + filePathSplitted.get(filePathSplitted.size() - 1));
    }

    private void zipFolder(Node rootNode) throws IOException {
        Path root = rootNode.getPath().toRealPath();
        // Path root = treatPath(rootNode.getPath());
        String rootName = root.getFileName().toString();
        //String zipName = "../" + FileNameUtils.getBaseName(root.toString()) + ".zip";
        String zipName = root.toAbsolutePath().getParent().toAbsolutePath().toString() + "/" + FileNameUtils.getBaseName(root.toString()) + ".zip";
        ZipArchiveOutputStream archive = new ZipArchiveOutputStream(new FileOutputStream(zipName));

        File directoryToArchive = new File(root.toString());

        for (Path path : Files.walk(directoryToArchive.toPath()).toList()) {
            File file = path.toFile();

            // FIXME: This is a hack to avoid to archive the zip file. But it avoids all zip files.
            if (!file.isDirectory()  && !FileNameUtils.getExtension(file.getName()).equals("zip")) {
                Path entry_path = getEntryPath(rootName, file);

                ZipArchiveEntry entry = new ZipArchiveEntry(file, entry_path.toString());
                FileInputStream fis = new FileInputStream(file);

                archive.putArchiveEntry(entry);
                IOUtils.copy(fis, archive);
                archive.closeArchiveEntry();
            }
        }

        archive.finish();
    }

    private boolean callCleanUp(Project project) {
        Feature cleanup = null;

        try {
            cleanup = project.getFeature(Mandatory.Features.Any.CLEANUP).get();
        } catch (NoSuchElementException e) {
            return false;
        }

        return cleanup.execute(project).isSuccess();
    }

    // - Execute function implementation
    @Override
    public ExecutionReport execute(Project project, Object... params) {
        if (!callCleanUp(project))
            return new ExecReport(ExecReport.Status.ERROR, "Couldn't cleanup the project");

        try {
            zipFolder(project.getRootNode());
        } catch (IOException e) {
            return new ExecReport(ExecReport.Status.ERROR, "Couldn't create the archive");
        }

        return new ExecReport(ExecReport.Status.SUCCESS);
    }
}
