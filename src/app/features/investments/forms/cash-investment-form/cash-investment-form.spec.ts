import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CashInvestmentForm } from './cash-investment-form';

describe('CashInvestmentForm', () => {
  let component: CashInvestmentForm;
  let fixture: ComponentFixture<CashInvestmentForm>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CashInvestmentForm],
    }).compileComponents();

    fixture = TestBed.createComponent(CashInvestmentForm);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
