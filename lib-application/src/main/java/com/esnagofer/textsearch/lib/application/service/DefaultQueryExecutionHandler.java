package com.esnagofer.textsearch.lib.application.service;

import com.esnagofer.textsearch.lib.application.usecase.Query;

public class DefaultQueryExecutionHandler<T extends Query<R>, R>
        implements QueryExecutionHandler<T,R> {

    protected R internalExecute(T query) {
        throw new UnsupportedOperationException("Not implemented!");
    }

    @Override
    public R execute(T command) {
        try {
            return internalExecute(command);
        } catch (Exception e) {
            throw e;
        }
    }

}
