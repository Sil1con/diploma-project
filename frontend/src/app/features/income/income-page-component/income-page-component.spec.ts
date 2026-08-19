import { ComponentFixture, TestBed } from '@angular/core/testing';

import { IncomePageComponenet } from './income-page-component';

describe('IncomePageComponenet', () => {
  let component: IncomePageComponenet;
  let fixture: ComponentFixture<IncomePageComponenet>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [IncomePageComponenet],
    }).compileComponents();

    fixture = TestBed.createComponent(IncomePageComponenet);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
