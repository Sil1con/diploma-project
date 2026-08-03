import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ExpendituresPageComponent } from './expenditures-page-component';

describe('ExpendituresPageComponent', () => {
  let component: ExpendituresPageComponent;
  let fixture: ComponentFixture<ExpendituresPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ExpendituresPageComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(ExpendituresPageComponent);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
