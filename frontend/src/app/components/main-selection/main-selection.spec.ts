import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MainSelection } from './main-selection';

describe('MainSelection', () => {
  let component: MainSelection;
  let fixture: ComponentFixture<MainSelection>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MainSelection],
    }).compileComponents();

    fixture = TestBed.createComponent(MainSelection);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
