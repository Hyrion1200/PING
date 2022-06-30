package fr.epita.assistants.myide.domain.entity.features.any;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import fr.epita.assistants.MyIde;
import fr.epita.assistants.myide.domain.entity.Mandatory;
import fr.epita.assistants.myide.domain.entity.Project;
import fr.epita.assistants.myide.domain.entity.features.Features;
import fr.epita.assistants.myide.domain.entity.features.exec_report.ExecReport;

public class Open extends Features {

    public Open() {
        super(Mandatory.Features.Any.OPEN);
    }

    @Override
    public ExecutionReport execute(Project project, Object... params) {
        String path = params[0].toString();
        String content = "";
        try {
            System.out.println(path);
            Path file = Paths.get(path);
            content = Files.readString(file);
            Project.filesContents.put(path, content);
        } catch (IOException e) {
            return new ExecReport(ExecReport.Status.ERROR, "Error reading the file " + path);
        }
        return new ExecReport(ExecReport.Status.SUCCESS, content, "Success opening file");
    }
    
}
