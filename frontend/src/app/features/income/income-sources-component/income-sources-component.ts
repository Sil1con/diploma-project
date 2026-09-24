import { Component, EventEmitter, Input, OnChanges, Output, SimpleChange, SimpleChanges } from '@angular/core';
import { IncomeSource } from '../utilities/models/income-source';
import { AsyncPipe, CurrencyPipe } from '@angular/common';
import { CapitalLetterPipe } from '../../../shared/pipes/capital-letter-pipe';
import { map, Observable } from 'rxjs';

@Component({
  selector: 'app-income-sources-component',
  imports: [
    AsyncPipe,
    CurrencyPipe,
    CapitalLetterPipe
  ],
  templateUrl: './income-sources-component.html',
  styleUrl: './income-sources-component.scss',
})
export class IncomeSourcesComponent implements OnChanges{
  @Input() incomeSources$!: Observable<IncomeSource[]>;
  @Input() incomeIcons!: Record<string, string>;

  @Output() viewAllOpened = new EventEmitter<boolean>();

  displayedIncomeSources$!: Observable<IncomeSource[]>;

  ngOnChanges(changes: SimpleChanges): void {
    if (changes['incomeSources$']) {
      this.getDisplayedIncomeSources();
    }
  }

  getDisplayedIncomeSources() {
    this.displayedIncomeSources$ = this.incomeSources$.pipe(
      map(incomeSources => incomeSources.slice(0, 4))
    );
  }

  openViewAll(): void {
    this.viewAllOpened.emit(true);
  }
}
