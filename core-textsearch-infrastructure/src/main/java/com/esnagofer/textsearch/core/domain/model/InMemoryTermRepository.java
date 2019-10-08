package com.esnagofer.textsearch.core.domain.model;

import com.esnagofer.textsearch.lib.domain.model.BaseRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public Term get(TermId termId) {
        throw new UnsupportedOperationException("Not implemented");
    }


    @Override
    public List<Term> get(TermId... termIds) {
        throw new UnsupportedOperationException("Not implemented");
    }

}
