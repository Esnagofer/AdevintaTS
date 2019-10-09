package com.esnagofer.textsearch.core.application.usecase.operations.search;

import com.esnagofer.textsearch.lib.Validate;
import com.esnagofer.textsearch.lib.application.usecase.Query;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

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

    public static Search of(String wordsToSearch) {
        List<String> wordsToSearchList = Arrays.stream(wordsToSearch.split(" "))
            .map(word -> word.replaceAll("\\s+",""))
            .map(word -> word.toLowerCase())
            .filter(word -> !"".equals(word.trim()))
            .collect(Collectors.toList());
        return new Search(wordsToSearchList);
    }

    public List<String> wordsToSearch() {
        return new ArrayList<>(wordsToSearch);
    }

}
