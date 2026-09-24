import { AsyncPipe, CurrencyPipe, DatePipe } from '@angular/common';
import { Component, EventEmitter, Input, Output, SimpleChanges } from '@angular/core';
import { map, Observable } from 'rxjs';
import { Expenditure } from '../../utilities/models/expenditure';
import { CapitalLetterPipe } from '../../../../shared/pipes/capital-letter-pipe';

@Component({
  selector: 'app-recent-expenditures-preview',
  imports: [
    DatePipe,
    AsyncPipe,
    CurrencyPipe,
    CapitalLetterPipe
  ],
  templateUrl: './recent-expenditures-preview.html',
  styleUrl: './recent-expenditures-preview.scss',
})
export class RecentExpendituresPreview {
  @Input() expenditures$!: Observable<Expenditure[]>;
  @Input() expenditureIcons!: Record<string, string>;

  @Output() viewAllOpened = new EventEmitter<boolean>();

  displayedExpenditures$!: Observable<Expenditure[]>;

  ngOnChanges(changes: SimpleChanges) {
    if (changes['expenditures$']) {
      this.getDisplayedExpenditures();
    }
  }

  getDisplayedExpenditures() {
    this.displayedExpenditures$ = this.expenditures$.pipe(
      map(expenditures => expenditures.reverse().slice(0, 6))
    );
  }

  openViewAll() {
    this.viewAllOpened.emit(true);
  }
}
