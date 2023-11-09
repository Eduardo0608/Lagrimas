import org.example.Main;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MainTest {
    @Test
    void shouldResultBeGreaterThanOneIfNumeratorIsGreaterThanDenominator() {
        final Main sut = new Main(); //Given
        final double obtained = sut.divide(5, 2); //When
        Assertions.assertTrue(obtained > 1); //Then
    }
}
