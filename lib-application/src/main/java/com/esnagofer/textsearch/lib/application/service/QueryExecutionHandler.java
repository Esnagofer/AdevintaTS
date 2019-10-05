package com.esnagofer.textsearch.lib.application.service;

import com.esnagofer.textsearch.lib.application.usecase.Query;

public interface QueryExecutionHandler<T extends Query<R>, R> {

    R execute(T query);

}
