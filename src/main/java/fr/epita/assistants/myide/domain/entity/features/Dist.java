package fr.epita.assistants.myide.domain.entity.features;

import fr.epita.assistants.myide.domain.entity.Feature;
import fr.epita.assistants.myide.domain.entity.Mandatory;
import fr.epita.assistants.myide.domain.entity.Node;
import fr.epita.assistants.myide.domain.entity.Project;
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
import java.util.NoSuchElementException;
import java.util.zip.Deflater;
import java.util.zip.ZipEntry;

public
class Dist extends Features {
    // - Constructor
    public Dist() {
    super(FeaturesTypes.Any.DIST);
  }

    // - Private methods
    private void zipFolder(Node rootNode) throws IOException {
	    String zipName = FileNameUtils.getBaseName(rootNode.getPath().toString()) + ".zip";
		ZipArchiveOutputStream archive = new ZipArchiveOutputStream(new FileOutputStream(zipName));

		archive.setLevel(Deflater.BEST_SPEED);
		archive.setMethod(ZipEntry.DEFLATED);

		File direcotryToArchive = new File(rootNode.getPath().toString());

		for (Path path : Files.walk(direcotryToArchive.toPath()).toList()) {
		    File file = path.toFile();

		    if (!file.isDirectory()) {
		        ZipArchiveEntry entry = new ZipArchiveEntry(file, file.toString());
		        FileInputStream fis = new FileInputStream(file);

		        archive.putArchiveEntry(entry);
		        IOUtils.copy(fis, archive);
		        archive.closeArchiveEntry();
		    }
		}
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
	@Override public ExecutionReport execute(Project project, Object... params) {
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
