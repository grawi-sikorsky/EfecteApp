import { Component } from '@angular/core';
import {NoteService} from "../services/note.service";
import {NoteDTO} from "../model/noteDTO";
import {CreateNoteDTO} from "../model/createNoteDTO";

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent {
  constructor(public noteService:NoteService) { }

  ngOnInit(): void {
  }

  testNote:CreateNoteDTO = {title:"title", content:"contentttt"};

  public getAllNotes(){
    this.noteService.getAllNotes().subscribe( data => {
      console.log(data);
    });
  }

  public getNote(id:string){
    this.noteService.getNote(id).subscribe( data => {
      console.log(data);
    });
  }
  public addNote(){
    this.noteService.addNote(this.testNote).subscribe( data => {
      console.log(data);
    });
  }
  public editNote(){
    this.noteService.editNote().subscribe( data => {
      console.log(data);
    });
  }
  public deleteNote(){
    this.noteService.deleteNote().subscribe( data => {
      console.log(data);
    });
  }
}
