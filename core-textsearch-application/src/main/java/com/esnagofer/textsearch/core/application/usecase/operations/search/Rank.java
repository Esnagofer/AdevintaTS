package com.esnagofer.textsearch.core.application.usecase.operations.search;

public class Rank {

    private String fileName;

    private int rank;

    private Rank(String fileName, int rank) {
        this.fileName = fileName;
        this.rank = rank;
    }

    public static Rank of(String fileName, int rank) {
        return new Rank(fileName, rank);
    }

    public String fileName() {
        return fileName;
    }

    public int rank() {
        return rank;
    }
}
