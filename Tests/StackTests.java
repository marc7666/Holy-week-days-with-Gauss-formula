import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StackTests {
    StackImpl<Integer> pila;

    @BeforeEach
    void setup() {
        pila = new StackImpl<>();
    }

    @Test
    void sizeTest() {
        assertEquals(0, pila.size());
    }

    @Test
    void pushTest() {
        pila.push(2);
        assertEquals(1, pila.size());
    }

    @Test
    void topTest() {
        pila.push(2);
        assertEquals(2, pila.top());
    }

    @Test
    void popTest() {
        pila.push(2);
        pila.pop();
        assertEquals(0, pila.size());
    }

    @Test
    void isEmptyTest() {
        assertTrue(pila.isEmpty());
        pila.push(56);
        assertFalse(pila.isEmpty());
    }
}
