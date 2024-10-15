package be.helmo.api.tools;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UtilsTests {

    @Test
    public void should_generate_different_group_code() {
        List<String> codes = List.of("A1b2C3", "D4e5F6", "G7h8I9", "J0k1L2", "M3n4O5", "P6q7R8",
                "S9t0U1", "V2w3X4", "Y5z6A7", "B8c9D0", "E1f2G3", "H4i5J6", "K7l8M9", "N0o1P2", "Q3r4S5",
                "T6u7V8", "W9x0Y1", "Z2a3B4", "C5d6E7", "F8g9H0", "I1j2K3", "L4m5N6", "O7p8Q9", "R0s1T2", "U3v4W5");

        Utils utils = new Utils(new Random());
        String generatedCode;
        for (int i = 0; i < 100; i++) {
            generatedCode = utils.generateCode(codes);
            assertFalse(codes.contains(generatedCode));
        }
    }
}
