package com.esnagofer.textsearch.lib.domain.model;

import com.esnagofer.textsearch.lib.Validate;

public class Aggregate<S extends Identity> {

	private S id;

	protected Aggregate(S id) {
		super();
		Validate.isNotNull(id, "id");
		this.id = id;
	}

	@Override
	public int hashCode() {
		return id.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (!(obj instanceof Aggregate)) return false;
		@SuppressWarnings("unchecked")
		Aggregate<S> other = (Aggregate<S>) obj;
		return id.equals(other.id());
	}

	public S id() {
		return id;
	}

}
