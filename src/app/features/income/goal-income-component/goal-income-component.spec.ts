import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GoalIncomeComponent } from './goal-income-component';

describe('GoalIncomeComponent', () => {
  let component: GoalIncomeComponent;
  let fixture: ComponentFixture<GoalIncomeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [GoalIncomeComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(GoalIncomeComponent);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
