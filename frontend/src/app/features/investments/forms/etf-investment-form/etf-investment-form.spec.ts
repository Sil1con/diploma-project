import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EtfInvestmentForm } from './etf-investment-form';

describe('EtfInvestmentForm', () => {
  let component: EtfInvestmentForm;
  let fixture: ComponentFixture<EtfInvestmentForm>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EtfInvestmentForm],
    }).compileComponents();

    fixture = TestBed.createComponent(EtfInvestmentForm);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
