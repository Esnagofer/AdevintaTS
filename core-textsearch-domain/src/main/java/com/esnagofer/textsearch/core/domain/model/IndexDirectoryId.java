package com.esnagofer.textsearch.core.domain.model;

import com.esnagofer.textsearch.lib.domain.model.StringIdentity;

public class IndexDirectoryId extends StringIdentity {

    private IndexDirectoryId(String value) {
        super(value);
    }

    public static IndexDirectoryId Of(String value) {
        return new IndexDirectoryId(value);
    }

}
