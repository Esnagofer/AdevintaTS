package com.esnagofer.textsearch.core.domain.service;

import com.esnagofer.textsearch.core.domain.model.FileId;
import com.esnagofer.textsearch.core.domain.model.Term;
import com.esnagofer.textsearch.core.domain.model.TermException;

import java.nio.file.Files;
import java.util.List;
import java.util.stream.Stream;

public class ExtractTermsService {

    private ExtractTermsService(){}

    public List<Term> extractTermList(FileId fileId) {
        try (Stream<String> lines = Files.lines(fileId.path())) {
            return Term.of(lines, fileId);
        } catch (Exception e) {
            throw new TermException(e);
        }
    }

    public static ExtractTermsService of() {
        return new ExtractTermsService();
    }

}
