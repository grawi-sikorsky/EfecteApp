package pl.js.efecteback.services;

import pl.js.efecteback.dto.NoteDTO;

import java.util.List;


public interface NoteService {

	NoteDTO addNote(NoteDTO noteToAdd);

	NoteDTO getNote(Long id);

	NoteDTO modifyNote(Long id, NoteDTO noteToModify);

	void removeNote(Long id);

	List<NoteDTO> getAllNotes();
}
