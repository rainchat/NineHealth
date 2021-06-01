package com.rainchat.ninehealth.utilitis.global;

public interface PlaceholderSupply<T> {
    Class<T> forClass();

    String getReplacement(String forKey);
}
