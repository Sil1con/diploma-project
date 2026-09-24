import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Expenditure } from '../../utilities/models/expenditure';
import { BehaviorSubject, combineLatest, map, Observable, take } from 'rxjs';
import { AsyncPipe, CurrencyPipe, DatePipe } from '@angular/common';
import { CapitalLetterPipe } from '../../../../shared/pipes/capital-letter-pipe';

@Component({
  selector: 'app-view-all-component',
  imports: [
    DatePipe,
    AsyncPipe,
    CurrencyPipe,
    CapitalLetterPipe
  ],
  templateUrl: './view-all-component.html',
  styleUrl: './view-all-component.scss',
})
export class ViewAllComponent {
  readonly expendituresPerPage: number = 8;

  @Input() expenditures$!: Observable<Expenditure[]>;
  @Input() expenditureIcons!: Record<string, string>;
  @Output() closed = new EventEmitter<boolean>();

  private currentPage$$ = new BehaviorSubject<number>(1);

  currentPage$ = this.currentPage$$.asObservable();
  displayedExpenditures$!: Observable<Expenditure[]>;
  totalPages$!: Observable<number>;

  ngOnInit() {
    this.prepareDisplayedInvestments();
    this.calculatePagesQuantity();
  }

  closeViewAll(): void {
    let isOpened = false;

    this.closed.emit(isOpened);
  }

  prepareDisplayedInvestments() {
    this.displayedExpenditures$ = combineLatest([
      this.expenditures$,
      this.currentPage$
    ]).pipe(
      map(([expenditures, currentPage]) => {
        const startIndex = (currentPage - 1) * this.expendituresPerPage;
        const endIndex = startIndex + this.expendituresPerPage;

        return expenditures.slice(startIndex, endIndex);
      })
    )
  }

  calculateDisplayedExpenditures(): Observable<number> {
    return combineLatest([
      this.expenditures$,
      this.displayedExpenditures$,
      this.currentPage$,
      this.totalPages$
    ]).pipe(
      map(([expenditures, displayedExpenditures, currentPage, totalPages]) => {
        if (totalPages === currentPage) return expenditures.length;
        else {
          return displayedExpenditures.length * currentPage;
        }
      })
    )
  }
  
  calculatePagesQuantity() {
    this.totalPages$ = this.expenditures$.pipe(
      map(expenditures =>
        Math.ceil(expenditures.length / this.expendituresPerPage)
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
