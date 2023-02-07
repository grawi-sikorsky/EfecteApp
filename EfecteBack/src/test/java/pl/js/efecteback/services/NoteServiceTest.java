/*
 * Copyright (c) 2023.
 * @author Jakub Sikora
 */

package pl.js.efecteback.services;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import pl.js.efecteback.dto.CreateModifyNoteDTO;
import pl.js.efecteback.dto.NoteDTO;
import pl.js.efecteback.exceptions.NoteNotFoundException;
import pl.js.efecteback.mapper.NoteMapper;
import pl.js.efecteback.model.NoteModel;
import pl.js.efecteback.repositories.NotesRepository;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class NoteServiceTest {
	@MockBean
	private NotesRepository notesRepository;
	@MockBean
	private NoteMapper noteMapper;
	@Autowired
	private NoteServiceImpl noteService;
	private static final CreateModifyNoteDTO slimDTO = new CreateModifyNoteDTO("a", "b");

	@BeforeEach
	void setUp() {
	}

	@AfterEach
	void tearDown() {
	}

	@Test
	void addNote_should_addNoteToDB() {
		NoteModel noteToAdd = new NoteModel(1L, "a", "b", ZonedDateTime.now());
		NoteModel noteAfterSave = new NoteModel(1L, "a", "b", noteToAdd.getDate());

		Mockito.doReturn(noteToAdd).when(noteMapper).createModifyNoteDtoToEntity(slimDTO);
		Mockito.doReturn(noteAfterSave).when(notesRepository).save(noteToAdd);

		noteService.addNote(slimDTO);
		Mockito.verify(notesRepository, Mockito.times(1)).save(noteToAdd);
		Mockito.verify(noteMapper).createModifyNoteDtoToEntity(slimDTO);
	}

	@Test
	void getNote_should_returnNote() {
		NoteDTO noteReturned = new NoteDTO(1L, "a", "b", ZonedDateTime.now());
		NoteModel noteExpected = new NoteModel(1L, "a", "b", ZonedDateTime.now());

		Mockito.when(notesRepository.findById(1L)).thenReturn(Optional.of(noteExpected));
		Mockito.when(noteMapper.noteToNoteDTO(noteExpected)).thenReturn(noteReturned);

		NoteDTO testNote = noteService.getNote(1L);

		Mockito.verify(notesRepository, Mockito.times(1)).findById(1L);
		Mockito.verify(noteMapper, Mockito.times(1)).noteToNoteDTO(noteExpected);
		assertEquals(testNote, noteReturned);
	}

	@Test
	void getNote_should_shouldThrowException() {
		assertThrows(NoteNotFoundException.class, () -> noteService.getNote(1L));
	}

	@Test
	void getAllNotes_should_returnListOfNotes() {
		List<NoteModel> notesList = new ArrayList<>();
		notesList.add(new NoteModel(1L, "a", "b", ZonedDateTime.now()));
		notesList.add(new NoteModel(2L, "a", "b", ZonedDateTime.now()));

		Mockito.when(notesRepository.findAll()).thenReturn(notesList);
		List<NoteDTO> noteDTOS = notesList.stream()
				.map(note -> noteMapper.noteToNoteDTO(note))
				.collect(Collectors.toList());

		List<NoteDTO> ex = noteService.getAllNotes();

		assertEquals(ex, noteDTOS);
	}

	@Test
	void modifyNote_should_changeValues() {
		CreateModifyNoteDTO noteDTOToModify = new CreateModifyNoteDTO("change", "change");
		NoteDTO noteExpected = new NoteDTO(1L, "change", "change", ZonedDateTime.now());
		NoteModel noteToModify = new NoteModel(1L, "nochange", "nochange", noteExpected.getDate());

		Mockito.when(notesRepository.findById(1L)).thenReturn(Optional.of(noteToModify));
		Mockito.when(noteMapper.noteToNoteDTO(noteToModify)).thenReturn(noteExpected);
		Mockito.when(notesRepository.save(noteToModify)).thenReturn(noteToModify);

		NoteDTO testNote = noteService.modifyNote(1L, noteDTOToModify);

		assertEquals(testNote.getTitle(), noteExpected.getTitle());
		assertEquals(testNote.getContent(), noteExpected.getContent());
		Mockito.verify(notesRepository, Mockito.times(1)).save(noteToModify);
	}

	@Test
	void modifyNote_should_throwException() {
		assertThrows(NoteNotFoundException.class, () -> noteService.getNote(666L));
	}

	@Test
	void removeNote_should_throwExceptionOnBadID() {
		assertThrows(NoteNotFoundException.class, () -> noteService.removeNote(666L));
	}

	@Test
	void removeNote_should_removeNote() {
		Mockito.when(notesRepository.existsById(1L)).thenReturn(true);
		noteService.removeNote(1L);

		Mockito.verify(notesRepository, Mockito.times(1)).deleteById(1L);
	}
}