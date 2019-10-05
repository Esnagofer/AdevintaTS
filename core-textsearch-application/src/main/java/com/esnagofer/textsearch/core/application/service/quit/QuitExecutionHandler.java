package com.esnagofer.textsearch.core.application.service.quit;

import com.esnagofer.textsearch.core.application.usecase.quit.Quit;
import com.esnagofer.textsearch.lib.application.service.CommandExecutionHandler;
import com.esnagofer.textsearch.lib.application.service.DefaultCommandExecutionHandler;

public class QuitExecutionHandler
    extends DefaultCommandExecutionHandler<Quit>
    implements CommandExecutionHandler<Quit> {

    protected QuitExecutionHandler() {}

    @Override
    protected void internalExecute(Quit command) {

    }

    public static CommandExecutionHandler<Quit> of() {
        return new QuitExecutionHandler();
    }

}
