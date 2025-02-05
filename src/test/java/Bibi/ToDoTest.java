package Bibi;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    public void testToDoString() {
        Todo t = new Todo("watch lecture");
        String expected = "[T][ ] watch lecture";
        assertEquals(expected, t.toString());
    }

    @Test
    public void testToDoMarKDone() {
        Todo t = new Todo("go for a jog");
        t.markDone();
        String expected = "[T][X] go for a jog";
        assertEquals(expected, t.toString());
    }
}
