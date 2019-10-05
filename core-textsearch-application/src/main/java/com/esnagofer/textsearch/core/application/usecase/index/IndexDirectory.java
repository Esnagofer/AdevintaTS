package com.esnagofer.textsearch.core.application.usecase.index;

import com.esnagofer.textsearch.lib.Validate;
import com.esnagofer.textsearch.lib.application.usecase.Command;

public class IndexDirectory extends Command {

    private String directoryPath;

    protected IndexDirectory(String directoryPath) {
        Validate.isNotBlank(directoryPath, "directoryPath");
        this.directoryPath = directoryPath;
    }

    public static IndexDirectory of(String directoryPath) {
        return new IndexDirectory(directoryPath);
    }

}
