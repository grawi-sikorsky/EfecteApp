import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {HttpClient, HttpClientModule} from '@angular/common/http';
import {AppComponent} from './app.component';
import {MaterialModule} from "./material/material.module";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {NotesviewComponent} from './notesview/notesview.component';
import {FormsModule} from "@angular/forms";
import {NoteComponent} from './notesview/note/note.component';

@NgModule({
  declarations: [
    AppComponent,
    NotesviewComponent,
    NoteComponent
  ],
  imports: [
    HttpClientModule,
    BrowserModule,
    MaterialModule,
    BrowserAnimationsModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
