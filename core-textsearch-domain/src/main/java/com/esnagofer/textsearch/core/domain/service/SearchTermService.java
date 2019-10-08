package com.esnagofer.textsearch.core.domain.service;

import com.esnagofer.textsearch.core.domain.model.RankedFile;
import com.esnagofer.textsearch.core.domain.model.TermId;
import com.esnagofer.textsearch.core.domain.model.TermRepository;
import com.esnagofer.textsearch.core.domain.model.TermRepositoryFactory;
import com.esnagofer.textsearch.lib.Validate;

import java.util.List;

public class SearchTermService {

    private TermRepository termRepository;

    private SearchTermService(TermRepository termRepository) {
        Validate.isNotNull(termRepository, "termRepository");
        this.termRepository = termRepository;
    }

    public static SearchTermService of() {
        return new SearchTermService(TermRepositoryFactory.of());
    }

    public List<RankedFile> search(List<TermId> terms) {
        return null;
    }

}
