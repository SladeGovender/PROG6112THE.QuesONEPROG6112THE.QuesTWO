import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestCalculateCommission_CalculatedUnsuccessfully {
    
    @Test
    void calculateCommission_CalculatedUnsuccessfully() {
        // Adjust the values to create a scenario where the calculation fails
        double commission = EstateAgent.calculateCommission("invalidPrice", "5.0");
        assertTrue(Double.isNaN(commission)); // Check for NaN (Not a Number)
    }
}
