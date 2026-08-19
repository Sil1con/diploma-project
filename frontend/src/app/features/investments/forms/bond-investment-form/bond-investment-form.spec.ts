import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BondInvestmentForm } from './bond-investment-form';

describe('BondInvestmentForm', () => {
  let component: BondInvestmentForm;
  let fixture: ComponentFixture<BondInvestmentForm>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [BondInvestmentForm],
    }).compileComponents();

    fixture = TestBed.createComponent(BondInvestmentForm);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
