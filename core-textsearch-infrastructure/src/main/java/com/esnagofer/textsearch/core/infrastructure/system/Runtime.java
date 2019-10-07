package com.esnagofer.textsearch.core.infrastructure.system;

import com.esnagofer.textsearch.core.application.service.index.IndexDirectoryExecutionHandler;
import com.esnagofer.textsearch.core.application.service.operations.start.StartExecutionHandler;
import com.esnagofer.textsearch.core.application.usecase.index.IndexDirectory;
import com.esnagofer.textsearch.core.application.usecase.operations.start.Start;

public class Runtime {

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

}