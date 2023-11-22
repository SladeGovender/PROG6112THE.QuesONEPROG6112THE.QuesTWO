import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestCalculateCommission_CalculatedSuccessfully {

    @Test
    void calculateCommission_CalculatedSuccessfully() {
        double commission = EstateAgent.calculateCommission("100000.0", "5.0");
        assertEquals(5000.0, commission, 0.01); // Tolerance is set to 0.01 for double comparison
    }
}
