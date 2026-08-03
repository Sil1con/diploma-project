import { Component } from '@angular/core';
import { OverviewComponent } from '../components/overview-component/overview-component';
import { SpendingCategoriesPreview } from '../components/spending-categories-preview/spending-categories-preview';
import { BudgetSummary } from '../components/budget-summary/budget-summary';
import { RecentTransactionsPreview } from '../components/recent-transactions-preview/recent-transactions-preview';

@Component({
  selector: 'app-expenditures-page-component',
  imports: [
    OverviewComponent,
    SpendingCategoriesPreview,
    BudgetSummary,
    RecentTransactionsPreview
  ],
  templateUrl: './expenditures-page-component.html',
  styleUrl: './expenditures-page-component.scss',
})
export class ExpendituresPageComponent {}
