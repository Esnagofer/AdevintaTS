package com.esnagofer.textsearch.application.usecase;

public class IndexDirectoryCommand {

    private String directoryPath;

    protected IndexDirectoryCommand(String directoryPath) {
        this.directoryPath = directoryPath;
    }

    public String directoryPath() {
        return directoryPath;
    }

    public IndexDirectoryCommand of(String directoryPath) {
        return new IndexDirectoryCommand(directoryPath);
    }

}
