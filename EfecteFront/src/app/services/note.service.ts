import {Injectable} from '@angular/core';
import * as http from "http";
import {HttpClient} from "@angular/common/http";
import {NoteDTO} from "../model/noteDTO";
import {environment} from "../../environments/environment";
import {CreateNoteDTO} from "../model/createNoteDTO";

@Injectable({
  providedIn: 'root'
})
export class NoteService {
  private API_URL = environment.API_URL;

  constructor(private http: HttpClient) {
  }

  public getAllNotes() {
    return this.http.get<NoteDTO>(this.API_URL + "/notes");
  }
  public getNote(id:string) {
    return this.http.get<NoteDTO>(this.API_URL + "/notes/" + id);
  }
  public addNote(createNoteDTO:CreateNoteDTO) {
    return this.http.post<NoteDTO>(this.API_URL + "/notes/add", createNoteDTO);
  }
  public editNote() {
    return this.http.get<NoteDTO>(this.API_URL + "/notes/2");
  }
  public deleteNote() {
    return this.http.get<NoteDTO>(this.API_URL + "/notes/2");
  }
}
