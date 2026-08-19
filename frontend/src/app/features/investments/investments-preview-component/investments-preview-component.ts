import { Component, Input, SimpleChanges } from '@angular/core';
import { Investment } from '../models/investments.model';
import { CurrencyPipe } from '@angular/common';
import { CapitalLetterPipe } from '../../../shared/pipes/capital-letter-pipe';

@Component({
  selector: 'app-investments-preview-component',
  imports: [
    CurrencyPipe,
    CapitalLetterPipe
  ],
  templateUrl: './investments-preview-component.html',
  styleUrl: './investments-preview-component.scss',
})
export class InvestmentsPreviewComponent {
  @Input() investments: Investment[] = [];
  previewInvestments: Investment[] = [];

  investmentIcons: Record<string, string> = {
    STOCK: 'assets/category_icons/stock.png',
    ETF: 'assets/category_icons/etf.png',
    CRYPTO: 'assets/category_icons/crypto.png',
    COMMODITY: 'assets/category_icons/commodity.png',
    BOND: 'assets/category_icons/bond.png',
    CASH: 'assets/category_icons/cash.png'
  };

  ngOnChanges(changes: SimpleChanges): void {
    if (changes['investments']) {
      this.sortInvestmentsDecreasing();
    }
  }

  sortInvestmentsDecreasing(): void {
    this.previewInvestments = [...this.investments]
      .sort(
        (a, b) =>
          (b.quantity * b.purchasePrice) -
          (a.quantity * a.purchasePrice)
      )
      .slice(0, 4);
  }
}
