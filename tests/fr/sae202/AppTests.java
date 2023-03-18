package fr.sae202;

import org.junit.Test;
import static org.junit.Assert.*;

public class AppTests {
    @Test public void appHasAGreeting() {
        assertEquals("Hello world", App.hello_world());
    }
}
