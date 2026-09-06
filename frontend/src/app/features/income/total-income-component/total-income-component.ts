import { AsyncPipe, CurrencyPipe } from '@angular/common';
import { Component, Input } from '@angular/core';
import { CustomPercentPipe } from '../../../shared/pipes/percent-pipe';
import { map, Observable, of } from 'rxjs';

@Component({
  selector: 'app-total-income-component',
  imports: [
    AsyncPipe,
    CurrencyPipe,
    CustomPercentPipe
  ],
  templateUrl: './total-income-component.html',
  styleUrl: './total-income-component.scss',
})
export class TotalIncomeComponent {
  @Input() totalIncomeValue$!: Observable<number>;
  @Input() previousMonthIncome!: number;

  prevMonthDifference$!: Observable<number>;

  constructor() {}

  ngOnInit() {
    this.calculatePrevMonthDiff();
  }

  calculatePrevMonthDiff() {
    this.prevMonthDifference$ = this.totalIncomeValue$.pipe(
      map(totalIncome => {
        if (this.previousMonthIncome === 0) {
          return 0;
        }

        const percent = ((totalIncome - this.previousMonthIncome) / this.previousMonthIncome) * 100

        return percent;
      })
    );
  }
}