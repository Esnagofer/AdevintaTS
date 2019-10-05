package com.esnagofer.textsearch.lib.application.service;

import com.esnagofer.textsearch.lib.application.usecase.Command;

public interface CommandExecutionHandler<T extends Command> {

    void execute(T command);

}
