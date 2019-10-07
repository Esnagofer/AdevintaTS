package com.esnagofer.textsearch.core.domain.model;

import com.esnagofer.textsearch.lib.domain.model.Aggregate;

import java.util.ArrayList;
import java.util.List;

public class Term extends Aggregate<TermId> {

    private List<FileId> filesContainingTerm = new ArrayList<>();

    protected Term(TermId id) {
        super(id);
    }

    public void isInFile(FileId fileId) {
        filesContainingTerm.add(fileId);
    }

    public List<FileId> filesContainingTerm() {
        return new ArrayList<>(filesContainingTerm);
    }

}
