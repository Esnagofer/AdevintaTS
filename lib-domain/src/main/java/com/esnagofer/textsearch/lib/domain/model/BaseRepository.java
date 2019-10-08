package com.esnagofer.textsearch.lib.domain.model;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public class BaseRepository<T extends Aggregate<S>, S extends Identity> implements Repository<T, S> {

    @Override
    public void add(T aggregate) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public void add(Set<T> aggregate) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public Optional<T> get(S aggregateId) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public List<T> get(S... aggregateIds) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public void remove(S aggregateId) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public boolean contains(S aggregateId) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public void merge(T aggregate) {
        throw new UnsupportedOperationException("Not implemented");
    }
}
