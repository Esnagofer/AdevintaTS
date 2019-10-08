package com.esnagofer.textsearch.core.application.service.operations.quit;

import com.esnagofer.textsearch.core.application.usecase.operations.quit.Quit;
import com.esnagofer.textsearch.core.domain.service.SearchTermService;
import com.esnagofer.textsearch.core.domain.service.TermReaderServiceFactory;
import com.esnagofer.textsearch.lib.application.service.CommandExecutionHandler;
import com.esnagofer.textsearch.lib.application.service.DefaultCommandExecutionHandler;

public class QuitExecutionHandler
    extends DefaultCommandExecutionHandler<Quit>
    implements CommandExecutionHandler<Quit> {

    private SearchTermService searchTermService;

    protected QuitExecutionHandler() {}

    @Override
    protected void internalExecute(Quit command) {
        TermReaderServiceFactory.of().stop();
    }

    public static CommandExecutionHandler<Quit> of() {
        return new QuitExecutionHandler();
    }

}
