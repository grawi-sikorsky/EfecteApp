package pl.js.efecteback.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.js.efecteback.model.NoteModel;

import java.util.List;

@Repository
public interface NotesRepository extends CrudRepository<NoteModel, Long> {
	NoteModel findNoteModelById(Long id);

	List<NoteModel> findAll();
}
