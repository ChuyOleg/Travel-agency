package com.oleh.chui.model.service.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

/**
 * Representing of PBKDF2 Password Encoder.
 *
 * @author Oleh Chui
 */
public class PBKDF2PasswordEncoder implements PasswordEncoder {

    private static final int ITERATION_COUNT = 65536;
    private static final int KEY_LENGTH = 128;
    private static final String ALGORITHM_NAME = "PBKDF2WithHmacSHA1";
    private static final byte[] SALT = {2, 4, 6, 8, 10, 2, 40, 35};
    private static final byte[] HEX_ARRAY = "0123456789ABCDEF".getBytes(StandardCharsets.US_ASCII);

    private static final Logger logger = LogManager.getLogger(PBKDF2PasswordEncoder.class);

    /**
     * Encodes password using PBKDF2WithHmacSHA1 algorithm.
     *
     * @param password String representing password that must be encoded.
     * @return String representing result of encoding password.
     */
    @Override
    public String encode(String password) {
        try {
            KeySpec spec = new PBEKeySpec(password.toCharArray(), SALT, ITERATION_COUNT, KEY_LENGTH);
            SecretKeyFactory factory = SecretKeyFactory.getInstance(ALGORITHM_NAME);
            return bytesToHex(factory.generateSecret(spec).getEncoded());
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            logger.error("Error during encoding password;");
            throw new RuntimeException(e.getMessage(), e.getCause());
        }
    }

    /**
     * Transforms byte[] to String.
     *
     * @param bytes Array of bytes.
     * @return String representing result of transformation byte[] to String.
     */
    private static String bytesToHex(byte[] bytes) {
        byte[] hexChars = new byte[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = HEX_ARRAY[v >>> 4];
            hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
        }
        return new String(hexChars, StandardCharsets.UTF_8);
    }

}
