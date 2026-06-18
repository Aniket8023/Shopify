import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ImagecarouselComponent } from './imagecarousel.component';

describe('ImagecarouselComponent', () => {
  let component: ImagecarouselComponent;
  let fixture: ComponentFixture<ImagecarouselComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ImagecarouselComponent]
    });
    fixture = TestBed.createComponent(ImagecarouselComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
