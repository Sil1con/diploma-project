import { Component, EventEmitter, Input, Output, SimpleChanges } from '@angular/core';
import { BehaviorSubject, combineLatest, map, Observable, take } from 'rxjs';
import { AsyncPipe, CurrencyPipe } from '@angular/common';
import { InvestmentSummary } from '../utilities/models/investment-summary.model';
import { CapitalLetterPipe } from '../../../shared/pipes/capital-letter-pipe';

@Component({
  selector: 'app-view-all-component',
  imports: [
    AsyncPipe,
    CurrencyPipe,
    CapitalLetterPipe
  ],
  templateUrl: './view-all-component.html',
  styleUrl: './view-all-component.scss',
})
export class ViewAllComponent {
  readonly investmentsPerPage: number = 8;

  @Input() investmentSummaries$!: Observable<InvestmentSummary[]>;
  @Output() closed = new EventEmitter<boolean>();

  private currentPage$$ = new BehaviorSubject<number>(1);

  currentPage$ = this.currentPage$$.asObservable();
  displayedInvestments$!: Observable<InvestmentSummary[]>;
  totalPages$!: Observable<number>;

  investmentIcons: Record<string, string> = {
    STOCK: 'assets/category_icons/stock.png',
    ETF: 'assets/category_icons/etf.png',
    CRYPTO: 'assets/category_icons/crypto.png',
    COMMODITY: 'assets/category_icons/commodity.png',
    BOND: 'assets/category_icons/bond.png',
    CASH: 'assets/category_icons/cash.png'
  };

  ngOnInit() {
    this.prepareDisplayedInvestments();
    this.calculatePagesQuantity();
  }

  closeViewAll(): void {
    let isOpened = false;

    this.closed.emit(isOpened);
  }

  prepareDisplayedInvestments() {
    this.displayedInvestments$ = combineLatest([
      this.investmentSummaries$,
      this.currentPage$
    ]).pipe(
      map(([investments, currentPage]) => {
        const startIndex = (currentPage - 1) * this.investmentsPerPage;
        const endIndex = startIndex + this.investmentsPerPage;

        return investments.slice(startIndex, endIndex);
      })
    )
  }

  calculateShownInvestments(): Observable<number> {
    return combineLatest([
      this.investmentSummaries$,
      this.displayedInvestments$,
      this.currentPage$,
      this.totalPages$
    ]).pipe(
      map(([investments, displayedInvestments, currentPage, totalPages]) => {
        if (totalPages === currentPage) return investments.length;
        else {
          return displayedInvestments.length * currentPage;
        }
      })
    )
  }
  
  calculatePagesQuantity() {
    this.totalPages$ = this.investmentSummaries$.pipe(
      map(investments =>
        Math.ceil(investments.length / this.investmentsPerPage)
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