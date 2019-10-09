package com.esnagofer.textsearch.core.domain.model;

import com.esnagofer.textsearch.lib.domain.model.Aggregate;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
        List<Term> out  = new ArrayList<>();
        Pattern pattern = Pattern.compile("[a-zA-Z0-9]+");
        for (String line: lines.collect(Collectors.toList())) {
            Matcher wordMatcher = pattern.matcher(line);
            while (wordMatcher.find()) {
                TermId termId = TermId.of(wordMatcher.group().toLowerCase());
                Term term = Term.of(termId);
                term.isInFile(fileId);
                out.add(term);
            }
        }
        return out;
    }

}
