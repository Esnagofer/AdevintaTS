package com.esnagofer.textsearch.core.domain.model;

import com.esnagofer.textsearch.lib.domain.model.StringIdentity;

public class TermId extends StringIdentity {

    private TermId(String value) {
        super(value);
    }

    public static TermId of(String term) {
        return new TermId(term);
    }

}
