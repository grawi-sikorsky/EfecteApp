import {Component, Input} from '@angular/core';
import {NoteService} from "../../services/note.service";
import {NoteDTO} from "../../model/noteDTO";

@Component({
  selector: 'app-note',
  templateUrl: './note.component.html',
  styleUrls: ['./note.component.css']
})
export class NoteComponent {
  @Input() note: NoteDTO = <NoteDTO>{};

  constructor(public noteService: NoteService) {
  }

  ngOnInit(): void {
  }

  isEditMode: boolean = false;
  editingNoteId: number = 0;

  enterEditMode(note: NoteDTO) {
    if (this.isEditMode && this.editingNoteId === note.id) {
      this.isEditMode = false;
      this.editingNoteId = 0;
    } else {
      this.isEditMode = true;
      this.editingNoteId = note.id;
    }
  }

  isInEditMode(note: NoteDTO): boolean {
    return this.isEditMode && this.editingNoteId === note.id;
  }

  public editNote(note: NoteDTO) {
    this.noteService.editNote(note).subscribe(data => {
      this.enterEditMode(note);
    });
  }

  public deleteNote(note: NoteDTO) {
    this.noteService.deleteNote(note).subscribe(data => {
      this.enterEditMode(note);
      this.noteService.getAllNotes();
    });
  }
}
