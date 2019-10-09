package com.esnagofer.textsearch.core.application.service.operations.search;

import com.esnagofer.textsearch.core.application.usecase.operations.search.Rank;
import com.esnagofer.textsearch.core.application.usecase.operations.search.Search;
import com.esnagofer.textsearch.core.application.usecase.operations.search.SearchResult;
import com.esnagofer.textsearch.core.domain.model.RankedFile;
import com.esnagofer.textsearch.core.domain.model.TermId;
import com.esnagofer.textsearch.core.domain.service.SearchTermService;
import com.esnagofer.textsearch.lib.Validate;
import com.esnagofer.textsearch.lib.application.service.DefaultQueryExecutionHandler;
import com.esnagofer.textsearch.lib.application.service.QueryExecutionHandler;

import java.util.List;
import java.util.stream.Collectors;

public class SearchExecutionHandler
    extends DefaultQueryExecutionHandler<Search, SearchResult>
    implements QueryExecutionHandler<Search, SearchResult> {

    private SearchTermService searchTermService;

    protected SearchExecutionHandler(SearchTermService searchTermService) {
        Validate.isNotNull(searchTermService, "searchTermService");
        this.searchTermService = searchTermService;
    }

    private Rank fromRankedFile(RankedFile rankedFile) {
        return Rank.of(
            rankedFile.fileId().path().getFileName().toString(),
            rankedFile.rank().value()
        );
    }

    @Override
    protected SearchResult internalExecute(Search query) {
        List<TermId> terms = query.wordsToSearch().stream()
            .map(TermId::of)
            .collect(Collectors.toList());
       List<RankedFile> rank = searchTermService.search(terms);
       return SearchResult.of(
           rank.stream()
               .map(this::fromRankedFile)
               .collect(Collectors.toList())
       );
    }

    public static QueryExecutionHandler<Search, SearchResult> of() {
        return new SearchExecutionHandler(SearchTermService.of());
    }

}
