package com.esnagofer.textsearch.core.application.usecase.operations.search;

import java.util.ArrayList;
import java.util.List;

public class SearchResult {

    private List<Rank> fileRank;

    private SearchResult(List<Rank> fileRank) {
        this.fileRank = fileRank;
    }

    public static SearchResult of(List<Rank> fileRank) {
        return new SearchResult(fileRank);
    }

    public List<Rank> fileRank() {
        return new ArrayList<>(fileRank);
    }
}
