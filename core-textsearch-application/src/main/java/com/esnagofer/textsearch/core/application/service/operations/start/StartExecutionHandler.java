package com.esnagofer.textsearch.core.application.service.operations.start;

import com.esnagofer.textsearch.core.application.usecase.operations.start.Start;
import com.esnagofer.textsearch.core.domain.service.SearchTermService;
import com.esnagofer.textsearch.core.domain.service.TermReaderServiceFactory;
import com.esnagofer.textsearch.lib.application.service.CommandExecutionHandler;
import com.esnagofer.textsearch.lib.application.service.DefaultCommandExecutionHandler;

public class StartExecutionHandler
    extends DefaultCommandExecutionHandler<Start>
    implements CommandExecutionHandler<Start> {

    private SearchTermService searchTermService;

    protected StartExecutionHandler() {}

    @Override
    protected void internalExecute(Start command) {
        TermReaderServiceFactory.of().start();
    }

    public static CommandExecutionHandler<Start> of() {
        return new StartExecutionHandler();
    }

}
