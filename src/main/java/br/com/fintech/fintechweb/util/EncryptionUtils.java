package br.com.fintech.fintechweb.util;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class EncryptionUtils {

    public static String encrypt(String password) throws Exception {
        MessageDigest md= MessageDigest.getInstance("MD5");

        md.update(password.getBytes(StandardCharsets.ISO_8859_1));

        BigInteger hash= new BigInteger(1, md.digest());

        return hash.toString(16);
    }

}