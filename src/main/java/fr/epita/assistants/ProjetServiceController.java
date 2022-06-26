package fr.epita.assistants;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.epita.assistants.myide.domain.entity.Project;
import fr.epita.assistants.myide.domain.service.ProjectServ;

@RestController
public class ProjetServiceController {
    
    @GetMapping("/ide")
    public ProjectServ projectServ(){
        return MyIde.projectServ;
    }

    @GetMapping("/ide/load")
    public Project load(@RequestParam(value="root", defaultValue = ".") String path)
    {
        return MyIde.projectServ.load(Paths.get(path));
    }
    
}
