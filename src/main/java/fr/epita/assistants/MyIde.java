package fr.epita.assistants;

import java.nio.file.Path;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import edu.emory.mathcs.backport.java.util.concurrent.atomic.AtomicLong;
import fr.epita.assistants.myide.domain.service.ProjectServ;
import fr.epita.assistants.myide.domain.service.ProjectService;
import fr.epita.assistants.utils.Given;

/**
 * Starter class, we will use this class and the init method to get a
 * configured instance of {@link ProjectService}.
 */
@Given(overridden = false)
@SpringBootApplication
public class MyIde {

    /**
     * Init method. It must return a fully functional implementation of {@link ProjectService}.
     *
     * @return An implementation of {@link ProjectService}.
     */

    public static void main(String[] args) {
		SpringApplication.run(MyIde.class, args);
        projectServ = new ProjectServ();
    }

    public static ProjectServ projectServ;

    /**
     * Record to specify where the configuration of your IDE
     * must be stored. Might be useful for the search feature.
     */
    public record Configuration(Path indexFile,
                                Path tempFolder) {
    }

}