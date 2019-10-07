package com.esnagofer.textsearch.core.application.service.operations.search;

import com.esnagofer.textsearch.core.application.usecase.operations.search.Search;
import com.esnagofer.textsearch.core.application.usecase.operations.search.SearchResult;
import com.esnagofer.textsearch.lib.application.service.DefaultQueryExecutionHandler;
import com.esnagofer.textsearch.lib.application.service.QueryExecutionHandler;

public class SearchExecutionHandler
    extends DefaultQueryExecutionHandler<Search, SearchResult>
    implements QueryExecutionHandler<Search, SearchResult> {

    protected SearchExecutionHandler() {}

    @Override
    protected SearchResult internalExecute(Search query) {
        throw new UnsupportedOperationException("Not implemented!");
    }

    public static QueryExecutionHandler<Search, SearchResult> of() {
        return new SearchExecutionHandler();
    }

}