import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { AppComponent } from './app.component';
import {MaterialModule} from "./material/material.module";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import { MainComponent } from './main/main.component';
import {FormsModule} from "@angular/forms";
import {NoteService} from "./services/note.service";

@NgModule({
  declarations: [
    AppComponent,
    MainComponent
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
export class AppModule { }
