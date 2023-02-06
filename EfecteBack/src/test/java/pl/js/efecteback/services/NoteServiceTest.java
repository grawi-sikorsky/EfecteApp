/*
 * Copyright (c) 2023.
 * @author Jakub Sikora
 */

package pl.js.efecteback.services;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import pl.js.efecteback.dto.CreateModifyNoteDTO;
import pl.js.efecteback.mapper.NoteMapper;
import pl.js.efecteback.model.NoteModel;
import pl.js.efecteback.repositories.NotesRepository;

import static org.junit.jupiter.api.Assertions.*;

//@SpringBootTest
class NoteServiceTest {
    @Mock
    private NotesRepository notesRepository;
    @SpyBean
    private NoteMapper noteMapper;
    @InjectMocks
    private NoteServiceImpl noteService;
    private static final CreateModifyNoteDTO slimDTO = new CreateModifyNoteDTO("test title","test content");

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void addNote_should_addNoteToDB() {
        NoteModel note = noteMapper.createModifyNoteDtoToEntity(slimDTO);
        noteService.addNote(slimDTO);
        Mockito.verify(notesRepository, Mockito.times(1)).save(note);
    }

    @Test
    void addNote_should_throwError() {
        noteService.addNote(slimDTO);

    }

    @Test
    void getNote() {
    }

    @Test
    void modifyNote() {
    }

    @Test
    void removeNote() {
    }

    @Test
    void getAllNotes() {
    }
}