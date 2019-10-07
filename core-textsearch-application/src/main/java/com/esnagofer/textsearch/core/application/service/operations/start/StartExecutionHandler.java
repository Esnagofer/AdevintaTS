package com.esnagofer.textsearch.core.application.service.operations.start;

import com.esnagofer.textsearch.core.application.usecase.operations.quit.Quit;
import com.esnagofer.textsearch.core.application.usecase.operations.start.Start;
import com.esnagofer.textsearch.lib.application.service.CommandExecutionHandler;
import com.esnagofer.textsearch.lib.application.service.DefaultCommandExecutionHandler;

public class StartExecutionHandler
    extends DefaultCommandExecutionHandler<Start>
    implements CommandExecutionHandler<Start> {

    protected StartExecutionHandler() {}

    @Override
    protected void internalExecute(Start command) {

    }

    public static CommandExecutionHandler<Start> of() {
        return new StartExecutionHandler();
    }

}
