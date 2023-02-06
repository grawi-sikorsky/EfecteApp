import {Component} from '@angular/core';
import {NoteService} from "../services/note.service";
import {NoteDTO} from "../model/noteDTO";
import {CreateNoteDTO} from "../model/createNoteDTO";

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent {
  constructor(public noteService: NoteService) {
  }

  ngOnInit(): void {
  }

  testNote: CreateNoteDTO = {
    title: "Note Title",
    content: "Lorem ipsum costam costam. Lorem ipsum costam costam. Lorem ipsum costam costam."
  };
  allNotes: NoteDTO[] = [];

  isEditMode: boolean = false;
  editingNoteId: number = 0;

  public getAllNotes() {
    this.noteService.getAllNotes().subscribe(data => {
      this.allNotes = data;
    });
  }

  public getNote(id: string) {
    this.noteService.getNote(id).subscribe(data => {
      console.log(data);
    });
  }

  public addNote() {
    this.noteService.addNote(this.testNote).subscribe(data => {
      console.log(data);
    });
  }

  public editNote(note: NoteDTO) {
    this.noteService.editNote(note).subscribe(data => {
      console.log(data);
    });
  }

  public deleteNote(note: NoteDTO) {
    this.noteService.deleteNote(note).subscribe(data => {
      this.enterEditMode(note);
      this.getAllNotes();
    });
  }

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
}
