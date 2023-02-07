/*
 * Copyright (c) 2023.
 * @author Jakub Sikora
 */

package pl.js.efecteback.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.js.efecteback.dto.CreateModifyNoteDTO;
import pl.js.efecteback.dto.NoteDTO;
import pl.js.efecteback.model.NoteModel;

import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class NoteMapperTest {

    @Autowired
    private NoteMapper noteMapper;

    @Test
    void noteToNoteDTO() {
        NoteModel note = new NoteModel(1L, "Title", "Content", ZonedDateTime.now());

        NoteDTO noteDTO = noteMapper.noteToNoteDTO(note);

        assertEquals(note.getId(),noteDTO.getId());
        assertEquals(note.getTitle(),noteDTO.getTitle());
        assertEquals(note.getContent(), noteDTO.getContent());
        assertEquals(note.getDate(),noteDTO.getDate());
    }

    @Test
    void noteToCreateModifyNoteDTO() {
        NoteModel note = new NoteModel(1L, "Title", "Content", ZonedDateTime.now());

        CreateModifyNoteDTO createDTO = noteMapper.noteToCreateModifyNoteDTO(note);

        assertEquals(note.getTitle(),createDTO.getTitle());
        assertEquals(note.getContent(), createDTO.getContent());
    }

    @Test
    void noteDtoToEntity() {
        NoteDTO noteDTO = new NoteDTO(1L, "Title", "Content", ZonedDateTime.now());

        NoteModel note = noteMapper.noteDtoToEntity(noteDTO);

        assertEquals(note.getId(),noteDTO.getId());
        assertEquals(note.getTitle(),noteDTO.getTitle());
        assertEquals(note.getContent(), noteDTO.getContent());
        assertEquals(note.getDate(),noteDTO.getDate());
    }

    @Test
    void createModifyNoteDtoToEntity() {
        CreateModifyNoteDTO createModifyNoteDTO = new CreateModifyNoteDTO("Title", "Content");

        NoteModel note = noteMapper.createModifyNoteDtoToEntity(createModifyNoteDTO);

        assertEquals(note.getTitle(),createModifyNoteDTO.getTitle());
        assertEquals(note.getContent(), createModifyNoteDTO.getContent());
    }
}