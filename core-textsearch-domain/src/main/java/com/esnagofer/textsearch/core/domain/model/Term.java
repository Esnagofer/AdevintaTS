package com.esnagofer.textsearch.core.domain.model;

import com.esnagofer.textsearch.lib.domain.model.Aggregate;

import java.util.ArrayList;
import java.util.List;

public class Term extends Aggregate<TermId> {

    private List<FileId> filesContainingTerm = new ArrayList<>();

    protected Term(TermId id) {
        super(id);
    }

    //  TODO: For concurrent indexing
    public synchronized void isInFile(FileId fileId) {
        if (!filesContainingTerm.contains(fileId)) {
            filesContainingTerm.add(fileId);
        }
    }

    //  TODO:   Improve the granularity of synchronized section in order to
    //          minimize blocking y a concurrent index process
    public void merge(Term termToMergeWith) {
        termToMergeWith.filesContainingTerm.stream().forEach(this::isInFile);
    }

    public List<FileId> filesContainingTerm() {
        return new ArrayList<>(filesContainingTerm);
    }

    public static Term of(TermId termId) {
        return new Term(termId);
    }

}
