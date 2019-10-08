package com.esnagofer.textsearch.core.infrastructure;

import com.esnagofer.textsearch.core.infrastructure.system.Runtime;

public class Boot {

    public static void main(String[] args) {
        Runtime.init(args[0]);
    }
}