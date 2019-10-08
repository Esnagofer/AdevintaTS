package com.esnagofer.textsearch.core.application.service.index;

import com.esnagofer.textsearch.core.application.usecase.index.IndexDirectory;
import com.esnagofer.textsearch.core.domain.model.IndexDirectoryId;
import com.esnagofer.textsearch.core.domain.service.IndexDirectoryService;
import com.esnagofer.textsearch.lib.Validate;
import com.esnagofer.textsearch.lib.application.service.CommandExecutionHandler;
import com.esnagofer.textsearch.lib.application.service.DefaultCommandExecutionHandler;

public class IndexDirectoryExecutionHandler
    extends DefaultCommandExecutionHandler<IndexDirectory>
    implements CommandExecutionHandler<IndexDirectory> {

    private IndexDirectoryService indexDirectoryService;

    protected IndexDirectoryExecutionHandler(IndexDirectoryService indexDirectoryService) {
        Validate.isNotNull(indexDirectoryService, "indexDirectoryService");
        this.indexDirectoryService = indexDirectoryService;
    }

    @Override
    protected void internalExecute(IndexDirectory command) {
        IndexDirectoryId indexDirectoryId = IndexDirectoryId.Of(command.directoryPath());
        indexDirectoryService.indexDirectory(indexDirectoryId);
    }

    public static CommandExecutionHandler<IndexDirectory> of() {
        IndexDirectoryService indexDirectoryService = IndexDirectoryService.of();
        return new IndexDirectoryExecutionHandler(indexDirectoryService);
    }

}
