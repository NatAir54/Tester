package org.example;

import lombok.Value;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Arrays;

@Value
public class Cryptographer {
    /* Encrypt text: Cryptographer.crypt(Cipher.ENCRYPT_MODE, originalText);
        Decrypt text: Cryptographer.crypt(Cipher.DECRYPT_MODE, textEncrypted); */
    private static final String KEY = "encryptDecryptAesKey";

    public static void main(String[] args) {
        String original = "secretSecretText";
        System.out.println("Original text: " + original);
        try {
            String textEncrypted = crypt(Cipher.ENCRYPT_MODE, original);
            System.out.println("Text encrypted: " + textEncrypted);
            String textDecrypted = crypt(Cipher.DECRYPT_MODE, textEncrypted);
            System.out.println("Text decrypted: " + textDecrypted);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public static String crypt(int mode, String value) throws Exception {
        byte[] valueBytes = value.getBytes(StandardCharsets.ISO_8859_1);
        byte[] keyBytes = KEY.getBytes(StandardCharsets.ISO_8859_1);

        MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
        keyBytes = messageDigest.digest(keyBytes);
        keyBytes = Arrays.copyOf(keyBytes, 16);

        SecretKeySpec secureKey = new SecretKeySpec(keyBytes, "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(mode, secureKey);

        byte[] finalValue = cipher.doFinal(valueBytes);

        return new String(finalValue, StandardCharsets.ISO_8859_1);
    }

}
