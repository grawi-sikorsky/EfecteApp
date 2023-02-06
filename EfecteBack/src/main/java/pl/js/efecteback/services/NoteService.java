package pl.js.efecteback.services;

import pl.js.efecteback.dto.CreateModifyNoteDTO;
import pl.js.efecteback.dto.NoteDTO;

import java.util.List;


public interface NoteService {

	NoteDTO addNote(CreateModifyNoteDTO noteToAdd);

	NoteDTO getNote(Long id);

	NoteDTO modifyNote(Long id, CreateModifyNoteDTO noteToModify);

	void removeNote(Long id);

	List<NoteDTO> getAllNotes();
}
