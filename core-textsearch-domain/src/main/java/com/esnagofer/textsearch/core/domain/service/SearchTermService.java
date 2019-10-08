package com.esnagofer.textsearch.core.domain.service;

import com.esnagofer.textsearch.core.infrastructure.domain.model.*;
import com.esnagofer.textsearch.lib.Validate;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SearchTermService {

    private boolean runCicle = true;

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

    public List<RankedFile> search(List<TermId> terms) {
        List<RankedFile> rankedFiles = new ArrayList<>();
        rankedFiles.add(RankedFile.of(FileId.ofPath("jander.txt"), Rank.of(100)));
        return rankedFiles;
    }

}
