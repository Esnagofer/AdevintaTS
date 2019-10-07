package com.esnagofer.textsearch.core.application.usecase.operations.start;

import com.esnagofer.textsearch.lib.application.usecase.Command;

public class Start extends Command {

    protected Start() {}

    public static Start of() {
        return new Start();
    }

}
