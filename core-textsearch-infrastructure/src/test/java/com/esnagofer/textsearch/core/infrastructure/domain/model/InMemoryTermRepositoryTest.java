package com.esnagofer.textsearch.core.infrastructure.domain.model;

import com.esnagofer.textsearch.core.domain.model.*;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryTermRepositoryTest {

    private Term createTerm(String id){
        TermId termId = TermId.of(id);
        Term term = Term.of(termId);
        return term;
    }

    private TermRepository repository(){
        return TermRepositoryFactory.of();
    }

    private void initRepository() {
        repository().add(createTerm("id1"));
        repository().add(createTerm("id2"));
    }

    @Test
    void merge() {
        Term term = createTerm("id1");
        term.isInFile(FileId.ofPath(Paths.get("path1.txt")));
        repository().add(term);
        Term term1 = createTerm("id1");
        term1.isInFile(FileId.ofPath(Paths.get("path2.txt")));
        repository().merge(term1);
        Optional<Term> optionalTerm = repository().get(TermId.of("id1"));
        assertTrue(optionalTerm.isPresent());
        assertEquals(2, optionalTerm.get().filesContainingTerm().size());
    }

    @Test
    void get() {
        initRepository();
        assertTrue(repository().contains(createTerm("id1").id()));
        assertTrue(repository().contains(createTerm("id2").id()));
        assertFalse(repository().contains(createTerm("id3").id()));
    }

}