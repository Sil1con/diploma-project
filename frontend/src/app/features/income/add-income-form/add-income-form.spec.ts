import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddIncomeForm } from './add-income-form';

describe('AddIncomeForm', () => {
  let component: AddIncomeForm;
  let fixture: ComponentFixture<AddIncomeForm>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AddIncomeForm],
    }).compileComponents();

    fixture = TestBed.createComponent(AddIncomeForm);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
