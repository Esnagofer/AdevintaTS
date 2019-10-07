package com.esnagofer.textsearch.lib.domain.model;

import com.esnagofer.textsearch.lib.Validate;

public class Identity<T> {

	private T value;

	public Identity(T value) {
		super();
		Validate.isNotNull(value, "value");
		this.value = value;
	}

	public T value() {
		return value;
	}

}
