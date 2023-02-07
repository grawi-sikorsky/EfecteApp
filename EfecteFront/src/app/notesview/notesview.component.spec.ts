import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NotesviewComponent } from './notesview.component';
import {HttpClientModule} from "@angular/common/http";
import {MaterialModule} from "../material/material.module";

describe('NotesviewComponent', () => {
  let component: NotesviewComponent;
  let fixture: ComponentFixture<NotesviewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NotesviewComponent ],
      imports: [
        HttpClientModule,
        MaterialModule
      ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(NotesviewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
