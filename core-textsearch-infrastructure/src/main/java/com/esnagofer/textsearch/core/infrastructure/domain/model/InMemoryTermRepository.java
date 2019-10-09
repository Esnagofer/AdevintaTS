package com.esnagofer.textsearch.core.infrastructure.domain.model;

import com.esnagofer.textsearch.core.domain.model.Term;
import com.esnagofer.textsearch.core.domain.model.TermId;
import com.esnagofer.textsearch.core.domain.model.TermRepository;
import com.esnagofer.textsearch.lib.domain.model.BaseRepository;

import java.util.*;
import java.util.stream.Collectors;

public class InMemoryTermRepository extends BaseRepository<Term, TermId> implements TermRepository {

    private Map<TermId, Term> termsInRepository = new HashMap<>();

    public InMemoryTermRepository(){}

    @Override
    public void add(Term term) {
        termsInRepository.put(term.id(), term);
    }

    //  TODO: Improve synchronization granularity
    @Override
    public synchronized void merge(Term newTermToMerge) {
        Term existentTerm = termsInRepository.get(newTermToMerge.id());
        if (existentTerm != null) {
            existentTerm.merge(newTermToMerge);
        } else {
            existentTerm = newTermToMerge;
        }
        add(existentTerm);
    }

    @Override
    public Optional<Term> get(TermId termId) {
        return Optional.ofNullable(termsInRepository.get(termId));
    }

    @Override
    public List<Term> get(TermId... termIds) {
        return Arrays.stream(termIds)
            .map(this::get)
            .filter(term -> term.isPresent())
            .map(term -> term.get())
            .collect(Collectors.toList());
    }

    @Override
    public boolean contains(TermId termId) {
        return termsInRepository.containsKey(termId);
    }

}
