package fr.epita.assistants.utils;

@Given()
@FunctionalInterface
public interface ThrowingSupplier<SUPPLY_T, THROWS_T extends Exception> {
    SUPPLY_T get() throws THROWS_T;
}
