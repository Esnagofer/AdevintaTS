package com.esnagofer.textsearch.core.infrastructure.termreader;

import com.esnagofer.textsearch.core.application.service.operations.quit.QuitExecutionHandler;
import com.esnagofer.textsearch.core.application.service.operations.search.SearchExecutionHandler;
import com.esnagofer.textsearch.core.application.usecase.operations.quit.Quit;
import com.esnagofer.textsearch.core.application.usecase.operations.search.Search;
import com.esnagofer.textsearch.core.application.usecase.operations.search.SearchResult;
import com.esnagofer.textsearch.core.domain.service.TermReaderService;

import java.util.Scanner;

public class CommandLineTermReaderService implements TermReaderService {

    private boolean runCicle = true;

    public CommandLineTermReaderService() {}

    @Override
    public void start() {
        try (Scanner keyboard = new Scanner(System.in)) {
            while (runCicle) {
                try { Thread.sleep(350); } catch (InterruptedException e) { }
                System.out.print("search> ");
                final String line = keyboard.nextLine();
                switch (line) {
                    case "":
                        break;
                    case ":quit":
                        QuitExecutionHandler.of().execute(Quit.of());
                        break;
                    default:
                        Search search = Search.of(line);
                        SearchResult searchResult = SearchExecutionHandler.of().execute(search);
                        if (searchResult.fileRank().size() == 0) {
                            // TODO: Implement with a Logger
                            System.out.println("no matches found");
                        } else {
                            searchResult.fileRank().stream().forEach(rank -> {
                                // TODO: Implement with a Logger
                                System.out.println(String.format("%s:%s%%", rank.fileName(), String.valueOf(rank.rank())));
                            });
                        }
                        break;
                }
            }
        }  catch (Exception e) {
            System.out.println(String.format("Ooopps: %s", e.getMessage()));
        }

    }

    @Override
    public void stop() {
        runCicle = false;
    }

}
