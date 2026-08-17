import { CurrencyPipe } from '@angular/common';
import { Component, Input } from '@angular/core';
import { CustomPercentPipe } from '../../../shared/pipes/percent-pipe';

@Component({
  selector: 'app-goal-income-component',
  imports: [
    CurrencyPipe,
    CustomPercentPipe
  ],
  templateUrl: './goal-income-component.html',
  styleUrl: './goal-income-component.scss',
})
export class GoalIncomeComponent {
  @Input() incomeGoal: number = 0;
  @Input() totalIncomeSourcesValue: number = 0;

  goalPercent!: number;

  ngOnInit() {
    this.calculateGoalPercent();
  }

  ngOnChanges() {
    this.calculateGoalPercent();
  }

  calculateGoalPercent() {
    const percent = ((this.totalIncomeSourcesValue / this.incomeGoal) * 100);

    this.goalPercent = percent;
  }
}
