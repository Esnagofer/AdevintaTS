package com.esnagofer.textsearch.lib.domain.model;

import com.esnagofer.textsearch.lib.Validate;

public class StringIdentity extends Identity<String>{
	
	public StringIdentity(String value) {
		super(value);
		Validate.isNotBlank(value, "value");
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((value() == null) ? 0 : value().hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (!(obj instanceof StringIdentity)) return false;
		StringIdentity other = (StringIdentity) obj;
		if (!value().equals(other.value())) return false;
		return true;
	}

}
