import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestCalculateCommission {

    @Test
    void calculateCommission_CalculatedSuccessfully() {
        double commission = EstateAgent.calculateCommission("100000.0", "5.0");
        assertEquals(5000.0, commission, 0.01); // Tolerance is set to 0.01 for double comparison
    }
    @Test
    void calculateCommission_CalculatedUnsuccessfully() {
        // Adjust the values to create a scenario where the calculation fails
        double commission = EstateAgent.calculateCommission("invalidPrice", "5.0");
        assertTrue(Double.isNaN(commission)); // Check for NaN (Not a Number)
    }
}
