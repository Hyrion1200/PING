package fr.epita.assistants.utils;

@Given()
@FunctionalInterface
public interface ThrowingRunnable<THROWS_T extends Exception> {
    void run() throws THROWS_T;
}
