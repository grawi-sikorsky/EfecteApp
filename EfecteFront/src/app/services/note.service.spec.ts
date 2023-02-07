import { TestBed } from '@angular/core/testing';

import { NoteService } from './note.service';
import {HttpClientModule} from "@angular/common/http";

describe('NoteService', () => {
  let service: NoteService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [
        HttpClientModule
      ]
    });
    service = TestBed.inject(NoteService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
