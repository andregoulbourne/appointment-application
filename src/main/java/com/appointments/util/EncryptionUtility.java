package com.appointments.util;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Component
public class EncryptionUtility {
    private static final Logger logger = LogManager.getLogger(EncryptionUtility.class);

    private static final String ALGORITHM = "AES/CBC/PKCS5Padding";
    private static final String AES = "AES";

    private final SecretKey secretKey;

    private Map<String, String> cache = new HashMap<>();

    public EncryptionUtility(){
        try {
            secretKey = generateKey();
        } catch(Exception e){
            logger.error(e);
            throw new IllegalStateException();
        }
    }

    public String encrypt(String input){
        String result = null;

        try {
            result = encrypt(ALGORITHM, input, secretKey);
            synchronized (cache){
                if(cache.containsValue(input)){
                    cache.remove(getCacheKeyByValue(input));
                }
            }
            cache.put(result, input);
        } catch(Exception e){
          logger.error(e);
        }

        return result;
    }

    public String encrypt(String algorithm, String input, SecretKey key) throws NoSuchPaddingException, NoSuchAlgorithmException,
            InvalidAlgorithmParameterException, InvalidKeyException,
            BadPaddingException, IllegalBlockSizeException {

        byte[] byteArray = new byte[16];
        new SecureRandom().nextBytes(byteArray);
        IvParameterSpec iv = new IvParameterSpec(byteArray);

        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.ENCRYPT_MODE, key, iv);
        byte[] cipherText = cipher.doFinal(input.getBytes());
        return Base64.getEncoder()
                .encodeToString(cipherText);
    }

    public String decrypt(String input){
        return cache.get(input);
    }

    private SecretKey generateKey() throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(AES);
        keyGenerator.init(256);
        return keyGenerator.generateKey();
    }

    private String getCacheKeyByValue(String value){
        return cache.entrySet()
                .stream()
                .filter(entry -> StringUtils.equals(entry.getValue(), value))
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse("");
    }
}
