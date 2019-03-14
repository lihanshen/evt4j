package io.everitoken.sdk.java.example;

import io.everitoken.sdk.java.PrivateKey;
import io.everitoken.sdk.java.PublicKey;

class PrivateKeyUsageExample {
    public static void main(String[] args) {
        PrivateKey privateKey = PrivateKey.randomPrivateKey();
        System.out.println(privateKey.toWif());

        PublicKey publicKey = privateKey.toPublicKey();

        PrivateKey seedPrivateKey = PrivateKey.seedPrivateKey("a random string");
        System.out.println(seedPrivateKey.toWif());

        PrivateKey privateKeyFromWif = PrivateKey.of("5J1by7KRQujRdXrurEsvEr2zQGcdPaMJRjewER6XsAR2eCcpt3D");
        System.out.println(privateKeyFromWif.toString());

        boolean valid = PrivateKey.isValidPrivateKey("5J1by7KRQujRdXrurEsvEr2zQGcdPaMJRjewER6XsAR2eCcpt3D");
        System.out.println(valid);
    }
}