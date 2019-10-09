package com.esnagofer.textsearch.core.domain.service;

import com.esnagofer.textsearch.core.domain.model.*;
import com.esnagofer.textsearch.lib.Validate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchTermService {

    private static SearchTermService searchTermService;

    private static final Integer mutex = 1;

    private TermRepository termRepository;

    private SearchTermService(TermRepository termRepository) {
        Validate.isNotNull(termRepository, "termRepository");
        this.termRepository = termRepository;
    }

    public static SearchTermService of() {
        if (searchTermService == null) {
            synchronized (mutex) {
                if (searchTermService == null) {
                    searchTermService = new SearchTermService(TermRepositoryFactory.of());
                }
            }
        }
        return searchTermService;
    }

    public List<RankedFile> search(List<TermId> termIds) {
        Map<FileId,Integer> fileHitCount = new HashMap<>();
        List<Term> terms = termRepository.get(termIds.toArray(new TermId[termIds.size()]));
        return RankedFile.of(termIds, fileHitCount, terms);
    }

}
