package fr.sae202;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AppTests {
    @Test public void appHasAGreeting() {
        assertEquals("Hello world", App.hello_world());
    }
}
