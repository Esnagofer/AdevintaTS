package com.esnagofer.textsearch.core.domain.model;

import com.esnagofer.textsearch.lib.domain.model.Aggregate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    //  TODO:   We can discuss about if this location for calculate Terms is the best
    //          It could be also implemented as a Service/VO: pros, cons , coupling ...
    public static List<Term> of(Stream<String> lines, FileId fileId){
        List<Term>out  = new ArrayList<>();
        out = lines
            // TODO: Improve parsing in order to detect signs as delimiter tokens
            .map(line0 -> line0.split("[\\s]+"))
            .flatMap(Arrays::stream)
            .distinct()
            .map(TermId::of)
            .map(Term::of)
            .map(term -> {
                term.isInFile(fileId);
                return term;
            })
            .collect(Collectors.toList());
        return out;
    }

}
