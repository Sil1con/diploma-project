import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewAllComponent } from './view-all-component';

describe('ViewAllComponent', () => {
  let component: ViewAllComponent;
  let fixture: ComponentFixture<ViewAllComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ViewAllComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(ViewAllComponent);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
