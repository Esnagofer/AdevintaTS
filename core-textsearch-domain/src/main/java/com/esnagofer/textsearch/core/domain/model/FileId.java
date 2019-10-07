package com.esnagofer.textsearch.core.domain.model;

import com.esnagofer.textsearch.lib.domain.model.StringIdentity;

import java.nio.file.Path;
import java.nio.file.Paths;

public class FileId extends StringIdentity {

    private FileId(String value) {
        super(value);
    }

    public static FileId ofPath(Path path) {
        return new FileId(path.toString());
    }

    public static FileId ofPath(String path) {
        return new FileId(path);
    }

    public Path path() {
        return Paths.get(value());
    }

}
