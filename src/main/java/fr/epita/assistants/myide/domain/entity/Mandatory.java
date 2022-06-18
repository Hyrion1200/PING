package fr.epita.assistants.myide.domain.entity;

import fr.epita.assistants.utils.Given;

/**
 * Compilation of all mandatory features that you need to implement.
 * <p>
 * They would not be placed here in real conditions, but we put them here
 * to let you free of implementing you class hierarchy as you want.
 * <p>
 * You can add any feature you like (without modifying this file,
 * for obvious reasons).
 */
@Given()
public enum Mandatory {
    ;

    public enum Aspects implements Aspect.Type {
        ANY,
        MAVEN,
        GIT
    }

    public enum Features {
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
