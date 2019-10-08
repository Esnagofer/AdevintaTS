package com.esnagofer.textsearch.core.domain.service;

import com.esnagofer.textsearch.core.domain.model.FileId;
import com.esnagofer.textsearch.core.domain.model.IndexDirectoryException;
import com.esnagofer.textsearch.core.domain.model.IndexDirectoryId;
import com.esnagofer.textsearch.core.domain.model.TermRepository;
import com.esnagofer.textsearch.lib.Validate;
import com.esnagofer.textsearch.lib.domain.model.RepositoryException;

import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class IndexDirectoryService {

    private ExtractTermsService extractTermsService;

    private TermRepository termRepository;

    private IndexDirectoryService(
        ExtractTermsService extractTermsService,
        TermRepository termRepository
    ) {
        Validate.isNotNull(extractTermsService, "parseFileService");
        Validate.isNotNull(termRepository, "termRepository");
        this.extractTermsService = extractTermsService;
        this.termRepository = termRepository;
    }

    private List<Path> filesInDirectory(IndexDirectoryId indexDirectoryId) {
        try (Stream<Path> walk = Files.walk(Paths.get(indexDirectoryId.value()))) {
            List<Path> result = walk.filter(Files::isRegularFile)
                .collect(Collectors.toList());
            return result;
        } catch (Exception e) {
            throw new IndexDirectoryException(e);
        }
    }

    public void indexDirectory(IndexDirectoryId indexDirectoryId) {
        filesInDirectory(indexDirectoryId).stream()
            .map(path -> extractTermsService.extractTermList(FileId.ofPath(path)))
            //  TODO:   implement in only one stream pipeline
            .forEach(terms -> {
                terms.stream().forEach(termRepository::merge);
            });
    }

    public static IndexDirectoryService of() {
        ExtractTermsService extractTermsService = ExtractTermsService.of();
        Object termRepositoryInstance = null;
        try {
            termRepositoryInstance = Class.forName("com.esnagofer.textsearch.core.domain.model.InMemoryTermRepository")
                .getDeclaredConstructor()
                .newInstance();
        } catch (Exception e) {
            throw new RepositoryException(e);
        }
        TermRepository termRepository = TermRepository.class.cast(termRepositoryInstance);
        return new IndexDirectoryService(extractTermsService, termRepository);
    }


}
