package pl.js.efecteback.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.js.efecteback.model.NoteModel;

import java.util.List;

public interface NotesRepository extends CrudRepository<NoteModel, Long> {
	NoteModel findNoteModelById(Long id);

	List<NoteModel> findAll();
}
