import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StockInvestmentForm } from './stock-investment-form';

describe('StockInvestmentForm', () => {
  let component: StockInvestmentForm;
  let fixture: ComponentFixture<StockInvestmentForm>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [StockInvestmentForm],
    }).compileComponents();

    fixture = TestBed.createComponent(StockInvestmentForm);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
