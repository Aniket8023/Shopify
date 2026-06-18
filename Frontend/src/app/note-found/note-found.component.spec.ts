import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NoteFoundComponent } from './note-found.component';

describe('NoteFoundComponent', () => {
  let component: NoteFoundComponent;
  let fixture: ComponentFixture<NoteFoundComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [NoteFoundComponent]
    });
    fixture = TestBed.createComponent(NoteFoundComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
