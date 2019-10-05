package com.esnagofer.textsearch.core.application.service.index;

import com.esnagofer.textsearch.core.application.usecase.index.IndexDirectory;
import com.esnagofer.textsearch.lib.application.service.CommandExecutionHandler;
import com.esnagofer.textsearch.lib.application.service.DefaultCommandExecutionHandler;

public class IndexDirectoryExecutionHandler
    extends DefaultCommandExecutionHandler<IndexDirectory>
    implements CommandExecutionHandler<IndexDirectory> {

    protected IndexDirectoryExecutionHandler() {}

    @Override
    protected void internalExecute(IndexDirectory command) {

    }

    public static CommandExecutionHandler<IndexDirectory> of() {
        return new IndexDirectoryExecutionHandler();
    }

}
