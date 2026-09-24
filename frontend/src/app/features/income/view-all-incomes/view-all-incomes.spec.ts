import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewAllIncomes } from './view-all-incomes';

describe('ViewAllIncomes', () => {
  let component: ViewAllIncomes;
  let fixture: ComponentFixture<ViewAllIncomes>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ViewAllIncomes],
    }).compileComponents();

    fixture = TestBed.createComponent(ViewAllIncomes);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
