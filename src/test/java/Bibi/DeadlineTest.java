package Bibi;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.time.LocalDateTime;

public class DeadlineTest {
    @Test
    public void testGetBy() {
        Deadline d = new Deadline("submit assg0", "2025-03-15 1800");
        LocalDateTime expectedDate = LocalDateTime.of(2025, 3, 15, 18, 0);
        assertEquals(expectedDate, d.getBy());
    }
}
