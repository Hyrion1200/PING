package fr.epita.assistants.myide.domain.entity.features;

import fr.epita.assistants.myide.domain.entity.Feature;
import fr.epita.assistants.myide.domain.entity.Project;

public abstract class Features implements Feature {
    protected Type type;

    public Features(Type type) {
        this.type = type;
    }

    public abstract ExecutionReport execute(Project project, Object... params);

    @Override
    public Feature.Type type() {
        return type;
    }

    public Type getType()
    {
        return this.type;
    }

    public enum FeaturesTypes {
        ;

        /**
         * Features for all projects.
         */
        public enum Any implements Feature.Type {
            /**
             * Remove all trash files.
             * Trash files are listed, line by line,
             * in a ".myideignore" file at the root of the project.
             */
            CLEANUP,

            /**
             * Remove all trash files and create an archive.
             */
            DIST,

            /**
             * Fulltext search over project files.
             */
            SEARCH
        }

        /**
         * Features for the git project type.
         */
        public enum Git implements Feature.Type {
            /**
             * Git pull, fast forward if possible.
             */
            PULL,

            /**
             * Git add.
             */
            ADD,

            /**
             * Git commit.
             */
            COMMIT,

            /**
             * Git push (no force).
             */
            PUSH
        }

        /**
         * Features for the maven project type.
         */
        public enum Maven implements Feature.Type {
            /**
             * mvn compile
             */
            COMPILE,

            /**
             * mvn clean
             */
            CLEAN,

            /**
             * mvn test
             */
            TEST,

            /**
             * mvn package
             */
            PACKAGE,

            /**
             * mvn install
             */
            INSTALL,

            /**
             * mvn exec
             */
            EXEC,

            /**
             * mvn dependency:tree
             */
            TREE
        }

    }
}
