package io.everitoken.sdk.java;

import static io.everitoken.sdk.java.PublicKey.isValidPublicKey;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import io.everitoken.sdk.java.exceptions.InvalidAddressException;

public class Address {
    private static final String nullAddress = "EVT00000000000000000000000000000000000000000000000000";
    private final String address;

    private Address(String address) {
        if (!isValidAddress(address)) {
            throw new InvalidAddressException(address);
        }

        this.address = address;
    }

    private Address(@NotNull PublicKey key) {
        address = key.toString();
    }

    @Contract(pure = true)
    public static String getNullAddress() {
        return nullAddress;
    }

    public static boolean isValidAddress(@NotNull String key) {
        if (key.equals(getNullAddress())) {
            return true;
        }

        if (key.length() == 53 && key.charAt(3) == '0') {
            return true;
        }

        return isValidPublicKey(key);
    }

    @NotNull
    @Contract("_ -> new")
    public static Address of(String address) {
        return new Address(address);
    }

    @NotNull
    @Contract("_ -> new")
    public static Address of(PublicKey key) {
        return new Address(key);
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return getAddress();
    }
}
