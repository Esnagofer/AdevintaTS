package com.esnagofer.textsearch.core.domain.service;

import com.esnagofer.textsearch.core.infrastructure.domain.model.*;
import com.esnagofer.textsearch.lib.Validate;

import java.util.*;

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

    public List<RankedFile> search(List<TermId> termIds) {
        Map<FileId,Integer> fileHitCount = new HashMap<>();
        List<Term> terms = termRepository.get(termIds.toArray(new TermId[termIds.size()]));
        terms.stream().forEach(term -> {
            term.filesContainingTerm().stream()
                .forEach(fileId -> {
                    Integer thisFileHitCount = fileHitCount.get(fileId);
                    if (thisFileHitCount == null) {
                        thisFileHitCount = 0;
                    }
                    thisFileHitCount++;
                    fileHitCount.put(fileId,thisFileHitCount);
            }   );
        });
        List<RankedFile> rankedFiles = new ArrayList<>();
        fileHitCount.keySet().stream().forEach(fileId -> {
            Integer thisFileHitCount = fileHitCount.get(fileId);
            Integer fileRank = (100 * thisFileHitCount / termIds.size());
            rankedFiles.add(RankedFile.of(fileId, Rank.of(fileRank)));
        });
        return rankedFiles;
    }

}
