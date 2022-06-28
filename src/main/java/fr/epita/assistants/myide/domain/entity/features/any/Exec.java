package fr.epita.assistants.myide.domain.entity.features.any;

import fr.epita.assistants.myide.domain.entity.Mandatory;
import fr.epita.assistants.myide.domain.entity.Project;
import fr.epita.assistants.myide.domain.entity.Project_Entity;
import fr.epita.assistants.myide.domain.entity.features.Features;
import fr.epita.assistants.myide.domain.entity.features.exec_report.ExecReport;

import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Pattern;

public class Exec extends Features {
    public Exec() {
        super(Mandatory.Features.Any.EXEC);
    }

    @Override
    public ExecutionReport execute(Project project, Object... params) {
        if (params.length != 1){
            return new ExecReport(ExecReport.Status.ERROR, "Wrong parameters");
        }
        Path path = null;
        try {
            for (Object param : params)
                path = Paths.get((String) param);
        } catch (InvalidPathException e){
            return new ExecReport(ExecReport.Status.ERROR, "Invalid Path");
        }
        finally {
            if (path == null)
                return new ExecReport(ExecReport.Status.ERROR, "no path given");
        }
        String results = null;
        try {
            if (Pattern.matches(".*.py", path.getFileName().toString())) {
                ProcessBuilder processBuilder = new ProcessBuilder("python", path.toString());
                processBuilder.redirectErrorStream(true);
                Process process = processBuilder.start();
                results = process.getOutputStream().toString();
            } else if (Pattern.matches(".*.js", path.getFileName().toString())) {
                ProcessBuilder processBuilder = new ProcessBuilder("node", path.toString());
                processBuilder.redirectErrorStream(true);
                Process process = processBuilder.start();
                results = process.getOutputStream().toString();
            } else {
                return new ExecReport(ExecReport.Status.ERROR, "File given is nether a python file or javascript file");
            }
        } catch (IOException e){
            return new ExecReport(ExecReport.Status.ERROR, "error when building process");
        }
        Project_Entity proj = (Project_Entity) project;
        proj.ExecResult = results;
        return new ExecReport(ExecReport.Status.SUCCESS, "success");
    }
}
