package com.esnagofer.textsearch.core.domain.service;

import com.esnagofer.textsearch.core.domain.model.FileId;
import com.esnagofer.textsearch.core.domain.model.TermId;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ParseFileService {

    private ParseFileService() {}

    public List<TermId> extractTermList(FileId fileId) {
        List<TermId> out  = new ArrayList<>();
        try (Stream<String> lines = Files.lines(fileId.path())) {
            out = lines
                // TODO: Improve parsing in order to detect signs as delimiter tokens
                .map(line0 -> line0.split("[\\s]+"))
                .flatMap(Arrays::stream)
                .distinct()
                .map(TermId::of)
                .collect(Collectors.toList());
            return out;
        } catch (Exception e) {
            throw new ParseFileException(e);
        }
    }

    public static ParseFileService of() {
        return new ParseFileService();
    }

}
