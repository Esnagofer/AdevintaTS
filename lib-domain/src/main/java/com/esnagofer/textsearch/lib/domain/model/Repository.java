package com.esnagofer.textsearch.lib.domain.model;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface Repository<T extends Aggregate<S>, S extends Identity> {

	void add(T aggregate);

	void add(Set<T> aggregate);
	
	Optional<T> get(S aggregateId);

	List<T> get(S... aggregateIds);

	void remove(S aggregateId);
	
	boolean contains(S aggregateId);

	void merge(T aggregate);



}
