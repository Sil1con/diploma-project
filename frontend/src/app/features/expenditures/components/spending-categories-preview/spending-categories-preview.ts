import { Component, Input, SimpleChanges } from '@angular/core';
import { map, Observable } from 'rxjs';
import { CategorySummary } from '../../utilities/models/category-summary';
import { AsyncPipe, CurrencyPipe } from '@angular/common';
import { CapitalLetterPipe } from '../../../../shared/pipes/capital-letter-pipe';
import { CustomPercentPipe } from '../../../../shared/pipes/percent-pipe';

@Component({
  selector: 'app-spending-categories-preview',
  imports: [
    AsyncPipe,
    CurrencyPipe,
    CustomPercentPipe,
    CapitalLetterPipe
  ],
  templateUrl: './spending-categories-preview.html',
  styleUrl: './spending-categories-preview.scss',
})
export class SpendingCategoriesPreview {
  private readonly budget: number = 2000;

  @Input() categoriesSummary$!: Observable<CategorySummary[]>;
  @Input() expenditureIcons!: Record<string, string>;

  displayedCategoriesSummary$!: Observable<CategorySummary[]>;

  ngOnChanges(changes: SimpleChanges) {
    if (changes['categoriesSummary$']) {
      this.getDisplayedCategoriesSummary();
    }
  }

  getDisplayedCategoriesSummary() {
    this.displayedCategoriesSummary$ = this.categoriesSummary$.pipe(
      map(categoriesSummary => categoriesSummary.slice(0, 5))
    );
  }

  calculatePercentFromBudget(categoryTotalValue: number): number {
    return ((categoryTotalValue / this.budget) * 100);
  }

  calculatePercentWidth(categoryTotalValue: number): number {
    const percent = ((categoryTotalValue / this.budget) * 100); 

    if (percent > 100) return 100;

    return percent;
  }
}
