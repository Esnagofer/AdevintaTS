package com.esnagofer.textsearch.core.domain.service;

import com.esnagofer.textsearch.lib.domain.model.RepositoryException;

public class TermReaderServiceFactory {

    private  static final String CMDLINE_CLASS_NAME = "com.esnagofer.textsearch.core.infrastructure.termreader.CommandLineTermReaderService";

    private static TermReaderService termReaderService;

    private static final Integer mutex = 1;

    public static TermReaderService of() {
        if (termReaderService == null) {
            synchronized (mutex) {
                if (termReaderService == null) {
                    Object termReaderServiceInstance = null;
                    try {
                        termReaderServiceInstance = Class.forName(CMDLINE_CLASS_NAME)
                            .getDeclaredConstructor()
                            .newInstance();
                    } catch (Exception e) {
                        throw new RepositoryException(e);
                    }
                    termReaderService = TermReaderService.class.cast(termReaderServiceInstance);
                }
            }
        }
        return termReaderService;
    }


}
