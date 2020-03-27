package br.com.nextgen2020.comandablue.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class EncryptDecrypt{

    // REF: https://stackoverflow.com/questions/5355466/converting-secret-key-into-a-string-and-vice-versa
    // REF: https://stackoverflow.com/questions/29354133/how-to-fix-invalid-aes-key-length/29354222

    private Cipher cipher;
    private byte[] decodedKey;
    SecretKey secretKey;

    public EncryptDecrypt() throws Exception {
        decodedKey = "969DF80BAC981429EAA5D9FF6E094FAF".getBytes();
        secretKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
        cipher = javax.crypto.Cipher.getInstance("AES");
    }

    public String encrypt(String plainText)
            throws Exception {
        byte[] plainTextByte = plainText.getBytes();
        cipher.init(javax.crypto.Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedByte = cipher.doFinal(plainTextByte);
        Base64.Encoder encoder = Base64.getEncoder();
        String encryptedText = encoder.encodeToString(encryptedByte);
        return encryptedText;
    }

    public String decrypt(String encryptedText)
            throws Exception {
        Base64.Decoder decoder = Base64.getDecoder();
        byte[] encryptedTextByte = decoder.decode(encryptedText);
        cipher.init(javax.crypto.Cipher.DECRYPT_MODE, secretKey);
        byte[] decryptedByte = cipher.doFinal(encryptedTextByte);
        String decryptedText = new String(decryptedByte);
        return decryptedText;
    }
}
