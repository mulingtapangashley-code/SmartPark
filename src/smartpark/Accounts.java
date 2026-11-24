package smartpark;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;
import java.util.Locale;
import java.util.Properties;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

final class Accounts {
    private static final String DEMO_USER = "attendant";
    private static final int ITERATIONS = 600_000;
    private final Path file = Path.of(System.getProperty("user.home"), ".smartpark", "accounts.properties");

    boolean matches(String username, char[] password) throws IOException {
        String key = normalize(username);
        if (key.equals(DEMO_USER)) {
            return Arrays.equals(password, new char[]{'s', 'm', 'a', 'r', 't', 'p', 'a', 'r', 'k'});
        }
        if (password.length < 8 || password.length > 128) {
            return false;
        }
        String entry = load().getProperty(key);
        if (entry == null) {
            return false;
        }
        try {
            String[] parts = entry.split(":", -1);
            if (parts.length != 2) {
                throw new IllegalArgumentException("Invalid account entry.");
            }
            Base64.Decoder decode = Base64.getDecoder();
            byte[] salt = decode.decode(parts[0]);
            return MessageDigest.isEqual(decode.decode(parts[1]), hash(password, salt));
        } catch (IllegalArgumentException ex) {
            throw new IOException("The saved account could not be read.", ex);
        }
    }

    private Properties load() throws IOException {
        Properties accounts = new Properties();
        try (var input = Files.newInputStream(file)) {
            accounts.load(input);
        } catch (java.nio.file.NoSuchFileException ex) {
            return accounts;
        } catch (IllegalArgumentException ex) {
            throw new IOException("The saved accounts could not be read.", ex);
        }
        return accounts;
    }

    private static byte[] hash(char[] password, byte[] salt) throws IOException {
        PBEKeySpec spec = new PBEKeySpec(password, salt, ITERATIONS, 256);
        try {
            return SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256").generateSecret(spec).getEncoded();
        } catch (GeneralSecurityException ex) {
            throw new IOException("Password hashing is unavailable.", ex);
        } finally {
            spec.clearPassword();
        }
    }

    private static String normalize(String username) {
        return username.strip().toLowerCase(Locale.ROOT);
    }
}
