import { AsyncPipe, CurrencyPipe } from '@angular/common';
import { Component, Input, OnChanges, SimpleChanges } from '@angular/core';
import { CustomPercentPipe } from '../../../shared/pipes/percent-pipe';
import { map, Observable, of } from 'rxjs';

@Component({
  selector: 'app-goal-income-component',
  imports: [
    AsyncPipe,
    CurrencyPipe,
    CustomPercentPipe
  ],
  templateUrl: './goal-income-component.html',
  styleUrl: './goal-income-component.scss',
})
export class GoalIncomeComponent {
  @Input() incomeGoal: number = 0;
  @Input() totalIncomeValue$!: Observable<number>;

  goalPercent$!: Observable<number>;

  ngOnInit() {
    this.calculateGoalPercent();
  }

  calculateGoalPercent() {
    this.goalPercent$ = this.totalIncomeValue$.pipe(
      map(totalIncome => {
        const percent = (totalIncome / this.incomeGoal) * 100;

        if (percent > 100) return 100;
        else return percent;
      })
    );
  }
}