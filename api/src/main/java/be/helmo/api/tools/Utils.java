package be.helmo.api.tools;
import java.util.*;

public class Utils {
    private final Random random;

    public Utils(Random random) {
        this.random = random;
    }

    public String generateCode(List<String> groupsCodes) {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        String numbers = "0123456789";
        List<Character> code = new ArrayList<>();
        for(int i = 0; i < 3; i++) {
            code.add(alphabet.charAt(random.nextInt(alphabet.length())));
            code.add(numbers.charAt(random.nextInt(numbers.length())));
        }

        Collections.shuffle(code);
        StringBuilder codeShuffled = new StringBuilder();
        for (char c : code) {
            codeShuffled.append(c);
        }

        String finalCode = codeShuffled.toString();
        return groupsCodes.contains(finalCode) ? generateCode(groupsCodes) : finalCode;
    }
}
