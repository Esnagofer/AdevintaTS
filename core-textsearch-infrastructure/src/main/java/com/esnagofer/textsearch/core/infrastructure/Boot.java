package com.esnagofer.textsearch.core.infrastructure;

import com.esnagofer.textsearch.core.infrastructure.system.Runtime;

import java.io.File;
import java.util.Scanner;

public class Boot {

    public static void main(String[] args) {
        Runtime.init(args[0]);
        /*
        final File indexableDirectory = new File(args[0]);
        //TODO: Index all files in indexableDirectory
        try (Scanner keyboard = new Scanner(System.in)) {
            while (true) {
                System.out.print("search> ");
                final String line = keyboard.nextLine();
                // TODO: Search indexed files for words in line
            }
        }
         */
    }
}