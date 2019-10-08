package com.esnagofer.textsearch.core.domain.model;

import com.esnagofer.textsearch.lib.domain.model.RepositoryException;

public class TermRepositoryFactory {

    private  static final String REPOSITORY_CLASS_NAME = "com.esnagofer.textsearch.core.domain.model.InMemoryTermRepository";

    private static TermRepository termRepository;

    private static final Integer mutex = 1;

    public static TermRepository of() {
        if (termRepository == null) {
            synchronized (mutex) {
                if (termRepository == null) {
                    Object termRepositoryInstance = null;
                    try {
                        termRepositoryInstance = Class.forName(REPOSITORY_CLASS_NAME)
                            .getDeclaredConstructor()
                            .newInstance();
                    } catch (Exception e) {
                        throw new RepositoryException(e);
                    }
                    termRepository = TermRepository.class.cast(termRepositoryInstance);
                }
            }
        }
        return termRepository;
    }


}
