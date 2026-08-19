import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CommodityInvestmentForm } from './commodity-investment-form';

describe('CommodityInvestmentForm', () => {
  let component: CommodityInvestmentForm;
  let fixture: ComponentFixture<CommodityInvestmentForm>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CommodityInvestmentForm],
    }).compileComponents();

    fixture = TestBed.createComponent(CommodityInvestmentForm);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
