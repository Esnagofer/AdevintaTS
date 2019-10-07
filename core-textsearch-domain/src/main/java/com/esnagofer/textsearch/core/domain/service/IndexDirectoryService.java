package com.esnagofer.textsearch.core.domain.service;

import com.esnagofer.textsearch.core.domain.model.IndexDirectoryId;
import com.esnagofer.textsearch.lib.Validate;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class IndexDirectoryService {

    private ParseFileService parseFileService;

    private IndexDirectoryService(ParseFileService parseFileService) {
        Validate.isNotNull(parseFileService, "parseFileService");
        this.parseFileService = parseFileService;
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
        List<Path> files = filesInDirectory(indexDirectoryId);

    }

    public static IndexDirectoryService of(ParseFileService parseFileService) {
        return new IndexDirectoryService(parseFileService);
    }


}
