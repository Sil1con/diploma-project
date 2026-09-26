import { AsyncPipe, CurrencyPipe, DatePipe } from '@angular/common';
import { Component, EventEmitter, Input, Output } from '@angular/core';
import { CapitalLetterPipe } from '../../../shared/pipes/capital-letter-pipe';
import { Observable, BehaviorSubject, combineLatest, map, take } from 'rxjs';
import { IncomeSource } from '../utilities/models/income-source';

@Component({
  selector: 'app-view-all-incomes',
  imports: [
    DatePipe,
    AsyncPipe,
    CurrencyPipe,
    CapitalLetterPipe
  ],
  templateUrl: './view-all-incomes.html',
  styleUrl: './view-all-incomes.scss',
})
export class ViewAllIncomes {
  readonly sourcesPerPage: number = 8;

  @Input() incomeSources$!: Observable<IncomeSource[]>;
  @Input() incomeIcons!: Record<string, string>;
  @Output() viewAllClosed = new EventEmitter<boolean>();
  @Output() deletedIncome = new EventEmitter<string>();

  private currentPage$$ = new BehaviorSubject<number>(1);

  currentPage$ = this.currentPage$$.asObservable();
  displayedIncomeSources$!: Observable<IncomeSource[]>;
  totalPages$!: Observable<number>;

  ngOnInit() {
    this.refreshIncomeData();

    this.totalPages$.subscribe(totalPages => {
      const currentPage = this.currentPage$$.value;

      if (currentPage > totalPages) {
        this.currentPage$$.next(
          currentPage - 1
        );
      }
    });
  }

  refreshIncomeData(): void {
    this.prepareDisplayedSources();
    this.calculatePagesQuantity();
  }

  closeViewAll(): void {
    let isOpened = false;

    this.viewAllClosed.emit(isOpened);
  }

  deleteIncome(incomeId: string) {
    this.deletedIncome.emit(incomeId);
  }

  prepareDisplayedSources() {
    this.displayedIncomeSources$ = combineLatest([
      this.incomeSources$,
      this.currentPage$
    ]).pipe(
      map(([incomeSources, currentPage]) => {
        const startIndex = (currentPage - 1) * this.sourcesPerPage;
        const endIndex = startIndex + this.sourcesPerPage;

        return incomeSources.slice(startIndex, endIndex);
      })
    )
  }

  calculateDisplayedIncomeSources(): Observable<number> {
    return combineLatest([
      this.incomeSources$,
      this.displayedIncomeSources$,
      this.currentPage$,
      this.totalPages$
    ]).pipe(
      map(([incomeSources, displayedIncomeSources, currentPage, totalPages]) => {
        if (totalPages === currentPage) return incomeSources.length;
        else {
          return displayedIncomeSources.length * currentPage;
        }
      })
    )
  }
  
  calculatePagesQuantity() {
    this.totalPages$ = this.incomeSources$.pipe(
      map(incomeSources =>
        Math.ceil(incomeSources.length / this.sourcesPerPage)
      )
    );
  }

  nextPage(): void {
    this.totalPages$
      .pipe(take(1))
      .subscribe(totalPages => {
        const currentPage = this.currentPage$$.value;

        if (currentPage < totalPages) {
          this.currentPage$$.next(currentPage + 1);
        }
      });
  }

  previousPage(): void {
    if (this.currentPage$$.value > 1) {
      this.currentPage$$.next(this.currentPage$$.value - 1);
    }
  }
}
