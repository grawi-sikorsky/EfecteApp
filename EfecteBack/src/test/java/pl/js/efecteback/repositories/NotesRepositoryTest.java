/*
 * Copyright (c) 2023.
 * @author Jakub Sikora
 */

package pl.js.efecteback.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import pl.js.efecteback.model.NoteModel;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Sql(scripts = "classpath:sql/insert-dummy-notes.sql")
class NotesRepositoryTest {

    @Autowired
    private NotesRepository notesRepository;

    @Test
    void findNoteModelById() {
    }

    @Test
    void findAll() {
        List<NoteModel> foundNotes = notesRepository.findAll();
        NoteModel note1 = foundNotes.get(0);
        NoteModel note2 = foundNotes.get(1);

        assertEquals(2, foundNotes.size());
        assertEquals(1L, note1.getId());
        assertEquals(2L, note2.getId());
    }
}