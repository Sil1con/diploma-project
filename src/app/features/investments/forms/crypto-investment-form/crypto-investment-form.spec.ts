import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CryptoInvestmentForm } from './crypto-investment-form';

describe('CryptoInvestmentForm', () => {
  let component: CryptoInvestmentForm;
  let fixture: ComponentFixture<CryptoInvestmentForm>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CryptoInvestmentForm],
    }).compileComponents();

    fixture = TestBed.createComponent(CryptoInvestmentForm);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
