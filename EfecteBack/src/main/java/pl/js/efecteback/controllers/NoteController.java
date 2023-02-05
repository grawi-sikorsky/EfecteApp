package pl.js.efecteback.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.js.efecteback.dto.NoteDTO;
import pl.js.efecteback.services.NoteService;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200", "*"})
@RestController
@RequestMapping("/notes")
public class NoteController {

	private final NoteService noteService;

	public NoteController(NoteService noteService) {
		this.noteService = noteService;
	}

	@PostMapping("add")
	public ResponseEntity<NoteDTO> addNote(@RequestBody NoteDTO noteToAdd) {
		return ResponseEntity.status(HttpStatus.OK).body(noteService.addNote(noteToAdd));
	}

	@GetMapping("{id}")
	public ResponseEntity<NoteDTO> getNote(@PathVariable Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(noteService.getNote(id));
	}

	@GetMapping
	public ResponseEntity<List<NoteDTO>> listAllNotes() {
		return ResponseEntity.status(HttpStatus.OK).body(noteService.getAllNotes());
	}

	@PatchMapping("{id}")
	public ResponseEntity<NoteDTO> updateNote(@PathVariable Long id, @RequestBody NoteDTO noteToModify) {
		return ResponseEntity.status(HttpStatus.OK).body(noteService.modifyNote(id, noteToModify));
	}

	@DeleteMapping("{id}")
	public ResponseEntity<Void> deleteNote(@PathVariable Long id) {
		noteService.removeNote(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}
