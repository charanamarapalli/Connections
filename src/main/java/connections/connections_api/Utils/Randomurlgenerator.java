package connections.connections_api.Utils;

import java.security.SecureRandom;
import java.util.Base64;

public class Randomurlgenerator {
	private static final SecureRandom secureRandom = new SecureRandom();

    public static String generateRandomUrl() {
        byte[] randomBytes = new byte[24];  // 24 bytes => 192 bits
        secureRandom.nextBytes(randomBytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(randomBytes); // Generate a URL-safe Base64 string
    }
}
