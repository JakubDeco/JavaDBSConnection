package sk.kosickaakademia.deco;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseTest {
    private Database database;

    @BeforeEach
    void setUp() {
        database = new Database("resource/config.properties");
    }

    @Test
    void getCountryCode3() {
        //correct
        assertEquals("SVK",database.getCountryCode3("  SLovakia "));
        assertEquals("SVK",database.getCountryCode3("sLovakia"));

        //incorrect
        assertNull(database.getCountryCode3("slowakia"));

    }
}