import { AsyncPipe, CurrencyPipe, PercentPipe } from '@angular/common';
import { Component, Input, SimpleChanges } from '@angular/core';
import { combineLatest, map, Observable, of } from 'rxjs';
import { CustomPercentPipe } from '../../../../shared/pipes/percent-pipe';

@Component({
  selector: 'app-budget-summary',
  imports: [
    AsyncPipe,
    CurrencyPipe,
    CustomPercentPipe
  ],
  templateUrl: './budget-summary.html',
  styleUrl: './budget-summary.scss',
})
export class BudgetSummary {
  @Input() budget$: Observable<number> = of(2000);
  @Input() totalSpent$!: Observable<number>;
  
  remaining$!: Observable<number>;

  ngOnChanges(changes: SimpleChanges) {
    if (changes['totalSpent$']) {
      this.calculateRemaining();
    }
  }

  calculateRemaining(): void {
    this.remaining$ = combineLatest([
      this.budget$,
      this.totalSpent$
    ]).pipe(
      map(([budget, spent]) => budget - spent)
    );
  }

  calculateSpentPercent(): Observable<number> {
    return combineLatest([
      this.budget$,
      this.totalSpent$
    ]).pipe(
      map(([budget, spent]) => (spent / budget) * 100)
    );
  }

  calculateBudgetLeft(): Observable<number> {
    return combineLatest([
      this.budget$,
      this.totalSpent$
    ]).pipe(
      map(([budget, spent]) => (1 - (spent / budget)) * 100)
    );
  }
}
