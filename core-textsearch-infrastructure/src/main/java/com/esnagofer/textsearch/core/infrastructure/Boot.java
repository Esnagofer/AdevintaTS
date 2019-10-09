package com.esnagofer.textsearch.core.infrastructure;

import com.esnagofer.textsearch.core.application.service.index.IndexDirectoryExecutionHandler;
import com.esnagofer.textsearch.core.application.service.operations.start.StartExecutionHandler;
import com.esnagofer.textsearch.core.application.usecase.index.IndexDirectory;
import com.esnagofer.textsearch.core.application.usecase.operations.start.Start;

public class Boot {

    private static void index(String directoryPath) {
        IndexDirectory indexDirectory = IndexDirectory.of(directoryPath);
        IndexDirectoryExecutionHandler.of().execute(indexDirectory);
    }

    private static void start() {
        Start start = Start.of();
        StartExecutionHandler.of().execute(start);
    }

    public static void init(String directoryPath) {
        index(directoryPath);
        start();
    }

    public static void main(String[] args) {
        try {
            Boot.init(args[0]);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}