package com.esnagofer.textsearch.core.domain.model;

import org.junit.jupiter.api.Test;

import java.nio.file.Paths;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RankedFileTest {

    private Term createTerm(String stringTermId, String... stringFileId) {
        TermId termId = TermId.of(stringTermId);
        Term term = Term.of(termId);
        Arrays.asList(stringFileId).stream().forEach(fileId -> {
            term.isInFile(FileId.ofPath(Paths.get(fileId)));
        });
        return term;
    }

    @Test
    void of() {
        List<Term> terms = new ArrayList<>();
        terms.add(createTerm("w1", "f1.txt", "f2.txt", "f4.txt"));
        terms.add(createTerm("w2", "f1.txt", "f3.txt", "f4.txt"));
        terms.add(createTerm("w3", "f1.txt", "f3.txt", "f4.txt"));
        terms.add(createTerm("w4", "f4.txt"));
        List<RankedFile> rankedFiles = RankedFile.of(terms);
        Map<FileId,Rank> mappedRank = new HashMap<>();
        rankedFiles.stream().forEach(rankedFile -> {
            mappedRank.put(rankedFile.fileId(), rankedFile.rank());
        });
        assertEquals(4, rankedFiles.size());
        assertEquals(75, mappedRank.get(FileId.ofPath(Paths.get("f1.txt"))).value());
        assertEquals(25, mappedRank.get(FileId.ofPath(Paths.get("f2.txt"))).value());
        assertEquals(50, mappedRank.get(FileId.ofPath(Paths.get("f3.txt"))).value());
        assertEquals(100, mappedRank.get(FileId.ofPath(Paths.get("f4.txt"))).value());
    }
}