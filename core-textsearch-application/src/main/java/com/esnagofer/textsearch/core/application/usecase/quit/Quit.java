package com.esnagofer.textsearch.core.application.usecase.quit;

import com.esnagofer.textsearch.lib.application.usecase.Command;

public class Quit extends Command {

    protected Quit() {}

    public static Quit of() {
        return new Quit();
    }

}
