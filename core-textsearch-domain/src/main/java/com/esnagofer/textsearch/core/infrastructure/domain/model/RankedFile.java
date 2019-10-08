package com.esnagofer.textsearch.core.infrastructure.domain.model;

import com.esnagofer.textsearch.lib.Validate;

public class RankedFile {

    private FileId fileId;

    private Rank rank;

    private RankedFile(FileId fileId, Rank rank) {
        Validate.isNotNull(rank,"rank");
        Validate.isNotNull(rank, "rank");
        this.fileId = fileId;
        this.rank = rank;
    }

    public static  RankedFile of(FileId fileId, Rank rank) {
        return new RankedFile(fileId, rank);
    }

    public FileId fileId() {
        return fileId;
    }

    public Rank rank() {
        return rank;
    }
}
