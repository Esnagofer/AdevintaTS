package com.esnagofer.textsearch.core.domain.service;

import com.esnagofer.textsearch.core.domain.model.*;
import com.esnagofer.textsearch.lib.Validate;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class IndexDirectoryService {

    private TermRepository termRepository;

    private IndexDirectoryService(
        TermRepository termRepository
    ) {
        Validate.isNotNull(termRepository, "termRepository");
        this.termRepository = termRepository;
    }

    private List<Path> filesInDirectory(IndexDirectoryId indexDirectoryId) {
        try (Stream<Path> walk = Files.walk(Paths.get(indexDirectoryId.value()))) {
            List<Path> result = walk.filter(Files::isRegularFile)
                .collect(Collectors.toList());
            //  TODO: Do not use this, It might be done with a logger
            System.out.println(
                String.format(
                    "%s files read in directory %s",
                    String.valueOf(result.size()),
                    indexDirectoryId.value()
                )
            );
            return result;
        } catch (Exception e) {
            throw new IndexDirectoryException(e);
        }
    }

    public List<Term> extractTermList(FileId fileId) {
        try (Stream<String> lines = Files.lines(fileId.path())) {
            return Term.of(lines, fileId);
        } catch (Exception e) {
            throw new TermException(e);
        }
    }

    public void indexDirectory(IndexDirectoryId indexDirectoryId) {
        filesInDirectory(indexDirectoryId).stream()
            .map(path -> this.extractTermList(FileId.ofPath(path)))
            //  TODO:   implement in only one stream pipeline
            .forEach(terms -> {
                terms.stream().forEach(termRepository::merge);
            });
    }

    public static IndexDirectoryService of() {
        TermRepository termRepository = TermRepositoryFactory.of();
        return new IndexDirectoryService(termRepository);
    }

}
