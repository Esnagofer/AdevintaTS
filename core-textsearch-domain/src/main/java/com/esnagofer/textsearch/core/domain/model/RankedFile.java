package com.esnagofer.textsearch.core.domain.model;

import com.esnagofer.textsearch.lib.Validate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RankedFile {

    private FileId fileId;

    private Rank rank;

    private RankedFile(FileId fileId, Rank rank) {
        Validate.isNotNull(rank,"rank");
        Validate.isNotNull(rank, "rank");
        this.fileId = fileId;
        this.rank = rank;
    }

    public static RankedFile of(FileId fileId, Rank rank) {
        return new RankedFile(fileId, rank);
    }

    //  TODO:   We can discuss about if this location for calculate rank is the best
    //          It could be also implemented as a Service/VO: pros, cons , coupling ...
    public static List<RankedFile> of(List<TermId> termIds, Map<FileId, Integer> fileHitCount, List<Term> terms) {
        terms.stream().forEach(term -> {
            term.filesContainingTerm().stream()
                .forEach(fileId -> {
                    Integer thisFileHitCount = fileHitCount.get(fileId);
                    if (thisFileHitCount == null) {
                        thisFileHitCount = 0;
                    }
                    thisFileHitCount++;
                    fileHitCount.put(fileId,thisFileHitCount);
                }   );
        });
        List<RankedFile> rankedFiles = new ArrayList<>();
        fileHitCount.keySet().stream().forEach(fileId -> {
            Integer thisFileHitCount = fileHitCount.get(fileId);
            Integer fileRank = (100 * thisFileHitCount / termIds.size());
            rankedFiles.add(RankedFile.of(fileId, Rank.of(fileRank)));
        });
        return rankedFiles;
    }

    public FileId fileId() {
        return fileId;
    }

    public Rank rank() {
        return rank;
    }
}
