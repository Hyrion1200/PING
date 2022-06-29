package fr.epita.assistants;

import java.nio.file.Path;
import java.util.HashMap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
    }

    public static HashMap<String, String> filesContent = new HashMap<String, String>();

    /**
     * Record to specify where the configuration of your IDE
     * must be stored. Might be useful for the search feature.
     */
    public record Configuration(Path indexFile,
                                Path tempFolder) {
    }

}