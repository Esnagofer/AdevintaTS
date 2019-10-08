package com.esnagofer.textsearch.core.domain.service;

import com.esnagofer.textsearch.core.infrastructure.domain.model.FileId;
import com.esnagofer.textsearch.core.infrastructure.domain.model.Term;
import com.esnagofer.textsearch.core.infrastructure.domain.model.TermException;
import com.esnagofer.textsearch.core.infrastructure.domain.model.TermId;

import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ExtractTermsService {

    private ExtractTermsService(){}

    public List<Term> extractTermList(FileId fileId) {
        List<Term>out  = new ArrayList<>();
        try (Stream<String> lines = Files.lines(fileId.path())) {
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
        } catch (Exception e) {
            throw new TermException(e);
        }
    }

    public static ExtractTermsService of() {
        return new ExtractTermsService();
    }

}
