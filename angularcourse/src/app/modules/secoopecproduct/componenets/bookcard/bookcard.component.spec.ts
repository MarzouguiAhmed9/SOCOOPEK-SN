import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BookcardComponent } from './bookcard.component';

describe('BookcardComponent', () => {
  let component: BookcardComponent;
  let fixture: ComponentFixture<BookcardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BookcardComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BookcardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
