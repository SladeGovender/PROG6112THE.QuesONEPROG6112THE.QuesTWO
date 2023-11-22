import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EstateAgentTest {

    @Test
    void validateData_AllValidInputs() {
        assertTrue(EstateAgent.validateData("Cape Town", "John Doe", "500000.0", "5.0"));
    }

    @Test
    void validateData_InvalidLocation() {
        assertFalse(EstateAgent.validateData("", "John Doe", "500000.0", "5.0"));
    }

    @Test
    void validateData_InvalidName() {
        assertFalse(EstateAgent.validateData("Cape Town", "", "500000.0", "5.0"));
    }

    @Test
    void validateData_InvalidPropertyPrice() {
        assertFalse(EstateAgent.validateData("Cape Town", "John Doe", "-500000.0", "5.0"));
    }

    @Test
    void validateData_InvalidCommission() {
        assertFalse(EstateAgent.validateData("Cape Town", "John Doe", "500000.0", "-5.0"));
    }
}
