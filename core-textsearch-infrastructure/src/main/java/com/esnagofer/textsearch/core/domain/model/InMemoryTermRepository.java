package com.esnagofer.textsearch.core.domain.model;

import com.esnagofer.textsearch.lib.domain.model.BaseRepository;

import java.util.*;
import java.util.stream.Collectors;

public class InMemoryTermRepository extends BaseRepository<Term, TermId> implements TermRepository  {

    private Map<TermId, Term> termsInRepository = new HashMap<>();

    public InMemoryTermRepository(){}

    //  TODO:
    @Override
    public synchronized void merge(Term newTermToMerge) {
        Term existentTerm = termsInRepository.get(newTermToMerge.id());
        if (existentTerm != null) {
            existentTerm.merge(newTermToMerge);
        } else {
            existentTerm = newTermToMerge;
        }
        termsInRepository.put(newTermToMerge.id(), existentTerm);
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

}
