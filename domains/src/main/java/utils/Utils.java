package utils;
import java.util.List;
import java.util.Random;

public class Utils {
    private Random random;

    public Utils(Random random) {
        this.random = random;
    }

    public String generateCode(List<String> groupsCodes) {
        String groupCode = "";
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        String numbers = "0123456789";

        for (int i = 0; i < 6; i++) {
            if (i < 3){
                int index = (int) (alphabet.length() * random.nextDouble());
                groupCode += alphabet.charAt(index);
            } else {
                int index = (int) (numbers.length() * random.nextDouble());
                groupCode += numbers.charAt(index);
            }
        }

        if (!groupsCodes.contains(groupCode)) {
            return groupCode;
        } else {
            return generateCode(groupsCodes);
        }
    }
}
