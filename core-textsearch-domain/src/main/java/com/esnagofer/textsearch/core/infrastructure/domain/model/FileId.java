package com.esnagofer.textsearch.core.infrastructure.domain.model;

import com.esnagofer.textsearch.lib.Validate;
import com.esnagofer.textsearch.lib.domain.model.StringIdentity;

import java.nio.file.Path;
import java.nio.file.Paths;

public class FileId extends StringIdentity {

    private FileId(Path path) {
        super(path.toString());
    }


    public static FileId ofPath(Path path) {
        Validate.isNotNull(path, "path");
        return new FileId(path);
    }

    public Path path() {
        return Paths.get(value());
    }

}
