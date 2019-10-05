package com.esnagofer.textsearch.lib.application.service;

import com.esnagofer.textsearch.lib.application.usecase.Command;

public class DefaultCommandExecutionHandler<T extends Command> implements CommandExecutionHandler<T> {

    private void beginTransaction() {}

    private void endTransaction() {}

    protected void internalExecute(T command) {
        throw new UnsupportedOperationException("Not implemented!");
    }

    @Override
    public void execute(T command) {
        try {
            beginTransaction();
            internalExecute(command);
            endTransaction();
        } catch (Exception e) {

        }
    }

}
