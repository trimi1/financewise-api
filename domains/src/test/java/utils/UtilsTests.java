package utils;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Random;

import static org.mockito.Mockito.*;

public class UtilsTests {
    @Test
    public void testGenerateGroupCode() {
        Random random = Mockito.mock(Random.class);
        Utils utils = new Utils(random);

        List<String> groupsCodes = List.of("ABC123", "DEF456", "GHI789");

        when(random.nextDouble()).thenReturn(0.0, 0.1, 0.2, 0.3, 0.4, 0.5);

        String groupCode = utils.generateCode(groupsCodes);

        verify(random, times(6)).nextDouble();
        verifyNoMoreInteractions(random);

        System.out.println(groupCode);
        assert groupCode.equals("AFK345");
    }
}
