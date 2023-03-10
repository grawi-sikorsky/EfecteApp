import {Component, QueryList, ViewChildren} from '@angular/core';
import {NoteService} from "../services/note.service";
import {NoteDTO} from "../model/noteDTO";
import {CreateNoteDTO} from "../model/createNoteDTO";
import {NoteComponent} from "./note/note.component";

@Component({
  selector: 'app-notesview',
  templateUrl: './notesview.component.html',
  styleUrls: ['./notesview.component.css']
})
export class NotesviewComponent {

  @ViewChildren(NoteComponent) noteChildren!: QueryList<NoteComponent>;

  constructor(public noteService: NoteService) {
  }

  ngOnInit(): void {
    this.getAllNotes();
  }

  testNote: CreateNoteDTO = {
    title: "New Note Title",
    content: "New Note Content. Lorem ipsum costam costam. Lorem ipsum costam costam. Lorem ipsum costam costam."
  };

  allNotes: NoteDTO[] = [];
  allNotesChildren: NoteComponent[] = [];


  public getAllNotes() {
    this.noteService.getAllNotes().subscribe(data => {
      this.allNotes = data;
      this.allNotesChildren = this.noteChildren.toArray();
    });
  }

  public getNote(id: string) {
    this.noteService.getNote(id).subscribe(data => {
    });
  }

  public addNote() {
    this.noteService.addNote(this.testNote).subscribe(data => {
      this.getAllNotes();
    });
  }

  removeChildNote(note: NoteDTO) {
    const index = this.allNotes.findIndex( el => el.id === note.id);
    if (index > -1) {
      this.allNotes.splice(index, 1);
    }
  }
}
