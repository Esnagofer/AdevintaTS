package com.esnagofer.textsearch.core.domain.model;

import org.junit.jupiter.api.Test;

import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class TermTest {

    private void addFilesToTerm(Term term, int howManyFiles) {
        for (;howManyFiles > 0; howManyFiles--){
            term.isInFile(
                FileId.ofPath(
                    Paths.get(String.format("filePath%s.txt", String.valueOf(howManyFiles)))
                )
            );
        }
    }

    private Term createTerm(String id){
        TermId termId = TermId.of(id);
        Term term = Term.of(termId);
        return term;
    }

    private void assertIsInFile(Term term, String filePath) {
        FileId fileId = FileId.ofPath(Paths.get(filePath));
        assertTrue(term.filesContainingTerm().contains(fileId));
    }

    private void assertContainsWord(List<Term> terms, String w1) {
        assertTrue(terms.contains(Term.of(TermId.of(w1))));
    }

    @Test
    void isInFile() {
        Term term = createTerm("id1");
        assertNotNull(term);
        addFilesToTerm(term, 2);
        assertEquals(2, term.filesContainingTerm().size());
        assertIsInFile(term, "filePath1.txt");
        assertIsInFile(term, "filePath2.txt");
    }

    @Test
    void merge() {
        Term term1 = createTerm("id1");
        assertNotNull(term1);
        term1.isInFile(FileId.ofPath(Paths.get("path1")));
        Term term2 = createTerm("id2");
        assertNotNull(term2);
        addFilesToTerm(term2, 2);
        term1.merge(term2);
        assertEquals(3, term1.filesContainingTerm().size());
        assertIsInFile(term1, "path1");
        assertIsInFile(term1, "filePath1.txt");
        assertIsInFile(term1, "filePath2.txt");
    }

    @Test
    void of() {
        Term term = createTerm("id1");
        assertNotNull(term);
        assertEquals("id1", term.id().value());
    }

    @Test
    void ofStream(){
        List<Term> terms = Term.of(
            Stream.of("w1 w2", "w2"),
            FileId.ofPath(Paths.get("path.txt"))
        );
        assertContainsWord(terms, "w1");
        assertContainsWord(terms, "w2");
        assertEquals(2, terms.size());
        assertEquals(1, terms.get(0).filesContainingTerm().size());
        assertEquals(1, terms.get(1).filesContainingTerm().size());
        assertEquals(Paths.get("path.txt"), terms.get(0).filesContainingTerm().get(0).path());
        assertEquals(Paths.get("path.txt"), terms.get(1).filesContainingTerm().get(0).path());
    }

}