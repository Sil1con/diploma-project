import { Component, EventEmitter, Input, OnChanges, Output, signal, SimpleChanges } from '@angular/core';
import { AsyncPipe, CurrencyPipe } from '@angular/common';
import { CapitalLetterPipe } from '../../../shared/pipes/capital-letter-pipe';
import { InvestmentSummary } from '../utilities/models/investment-summary.model';
import { map, Observable } from 'rxjs';

@Component({
  selector: 'app-investments-preview-component',
  imports: [
    AsyncPipe,
    CurrencyPipe,
    CapitalLetterPipe
  ],
  templateUrl: './investments-preview-component.html',
  styleUrl: './investments-preview-component.scss',
})
export class InvestmentsPreviewComponent implements OnChanges{
  protected userId: string = '1';

  @Input() investmentSummaries$!: Observable<InvestmentSummary[]>;
  @Output() viewAllOpened = new EventEmitter<boolean>();

  displayedInvestments$!: Observable<InvestmentSummary[]>;

  constructor() {}

  investmentIcons: Record<string, string> = {
    STOCK: 'assets/category_icons/stock.png',
    ETF: 'assets/category_icons/etf.png',
    CRYPTO: 'assets/category_icons/crypto.png',
    COMMODITY: 'assets/category_icons/commodity.png',
    BOND: 'assets/category_icons/bond.png',
    CASH: 'assets/category_icons/cash.png'
  };

  ngOnInit() {
    this.getDisplayedInvestments();
  }

   ngOnChanges(changes: SimpleChanges): void {
    if (changes['previewInvestments$']) {
      this.getDisplayedInvestments();
    }
  }

  getDisplayedInvestments() {
    this.displayedInvestments$ = this.investmentSummaries$.pipe(
      map(investments => investments.slice(0, 4))
    );
  }

  openViewAll() {
    this.viewAllOpened.emit(true);
  }
}
