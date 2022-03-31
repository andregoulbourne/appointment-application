import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FavoriteColorComponentComponent } from './favorite-color-component.component';

describe('FavoriteColorComponentComponent', () => {
  let component: FavoriteColorComponentComponent;
  let fixture: ComponentFixture<FavoriteColorComponentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FavoriteColorComponentComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FavoriteColorComponentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
