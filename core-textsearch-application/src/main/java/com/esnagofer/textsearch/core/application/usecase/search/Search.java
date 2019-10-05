package com.esnagofer.textsearch.core.application.usecase.search;

import com.esnagofer.textsearch.lib.Validate;
import com.esnagofer.textsearch.lib.application.usecase.Query;

import java.util.List;

public class Search extends Query<SearchResult> {

    private List<String> wordsToSearch;

    protected Search(List<String> wordsToSearch) {
        Validate.isNotNull(wordsToSearch, "wordsToSearch");
        Validate.isTrue(wordsToSearch.size() > 0, "Must have at least 1 word to search");
        this.wordsToSearch = wordsToSearch;
    }

    public static Search of(List<String> wordsToSearch) {
        return new Search(wordsToSearch);
    }

}
