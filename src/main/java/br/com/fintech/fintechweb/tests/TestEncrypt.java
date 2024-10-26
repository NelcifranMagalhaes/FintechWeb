package br.com.fintech.fintechweb.tests;

import br.com.fintech.fintechweb.util.EncryptionUtils;

public class TestEncrypt {

    public static void main(String[] args) {
        try {
            System.out.println(EncryptionUtils.encrypt("123456"));
            System.out.println(EncryptionUtils.encrypt("123456"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}