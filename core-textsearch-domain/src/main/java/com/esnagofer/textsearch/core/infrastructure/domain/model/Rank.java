package com.esnagofer.textsearch.core.infrastructure.domain.model;

public class Rank {

    private int value;

    private Rank(int value) {
        validateRankValue(value);
        this.value = value;
    }

    private void validateRankValue(int value) {
        if (value < 0 || value > 100) {
            throw new IllegalStateException(String.format("Rank out of range: %s", String.valueOf(value)));
        }
    }

    public static Rank of(int value) {
        return new Rank(value);
    }

    public int value() {
        return value;
    }

}
