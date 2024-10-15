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
        String alphanumeric = alphabet + numbers;

        for (int i = 0; i < 6; i++) {
            int index = (int) (alphanumeric.length() * random.nextDouble());
            groupCode += alphanumeric.charAt(index);
        }

        if (!groupsCodes.contains(groupCode)) {
            return groupCode;
        } else {
            return generateCode(groupsCodes);
        }
    }
}
