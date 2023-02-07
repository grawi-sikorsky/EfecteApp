package pl.js.efecteback.services;

import org.springframework.stereotype.Service;
import pl.js.efecteback.dto.CreateModifyNoteDTO;
import pl.js.efecteback.dto.NoteDTO;
import pl.js.efecteback.exceptions.NoteNotFoundException;
import pl.js.efecteback.mapper.NoteMapper;
import pl.js.efecteback.model.NoteModel;
import pl.js.efecteback.repositories.NotesRepository;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class NoteServiceImpl implements NoteService {

	private final NotesRepository noteRepository;
	private final NoteMapper noteMapper;

	public NoteServiceImpl(NotesRepository noteRepository, NoteMapper noteMapper) {
		this.noteRepository = noteRepository;
		this.noteMapper = noteMapper;
	}

	@Override
	public NoteDTO addNote(CreateModifyNoteDTO noteToAdd) {
		NoteModel noteToSave = noteMapper.createModifyNoteDtoToEntity(noteToAdd);
		return noteMapper.noteToNoteDTO(noteRepository.save(noteToSave));
	}

	@Override
	public NoteDTO getNote(Long id) {
		return noteMapper.noteToNoteDTO(getNoteById(id));
	}

	@Override
	public List<NoteDTO> getAllNotes() {
		return noteRepository.findAll().stream()
				.map(note -> noteMapper.noteToNoteDTO(note))
				.collect(Collectors.toList());
	}

	@Override
	public NoteDTO modifyNote(Long id, CreateModifyNoteDTO noteToModify) {
		NoteModel noteToSave = getNoteById(id);
		noteToSave.setTitle(noteToModify.getTitle());
		noteToSave.setContent(noteToModify.getContent());
		return noteMapper.noteToNoteDTO(noteRepository.save(noteToSave));
	}

	@Override
	public void removeNote(Long id) {
		if (!noteRepository.existsById(id)) {
			throw new NoteNotFoundException("Note with the specified ID is not present in the database.");
		}
		noteRepository.deleteById(id);
	}

	private NoteModel getNoteById(Long id) {
		return noteRepository.findById(id).orElseThrow(
				() -> new NoteNotFoundException("Note with the specified ID is not present in the database.")
		);
	}
}
