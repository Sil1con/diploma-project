import { Component, output } from '@angular/core';
import { OverviewComponent } from '../components/overview-component/overview-component';
import { SpendingCategoriesPreview } from '../components/spending-categories-preview/spending-categories-preview';
import { BudgetSummary } from '../components/budget-summary/budget-summary';
import { RecentTransactionsPreview } from '../components/recent-transactions-preview/recent-transactions-preview';
import { AddExpenseForm } from '../components/add-expense-form/add-expense-form';
import { ExpensePayload } from '../models/expense-payload.model';

@Component({
  selector: 'app-expenditures-page-component',
  imports: [
    OverviewComponent,
    SpendingCategoriesPreview,
    BudgetSummary,
    RecentTransactionsPreview,
    AddExpenseForm
  ],
  templateUrl: './expenditures-page-component.html',
  styleUrl: './expenditures-page-component.scss',
})
export class ExpendituresPageComponent {
  isAddExpenseFormOpened = false;

  expenses: ExpensePayload[] = [];

  openAddExpenseForm(): void {
    this.isAddExpenseFormOpened = true;
  }

  closeAddExpenseForm(): void {
    this.isAddExpenseFormOpened = false;
  }

  handleExpenseSubmitted(expense: ExpensePayload): void {
    // Demo implementation:
    this.expenses = [expense, ...this.expenses];

    // Later:
    // this.expenseService.createExpense(expense).subscribe(...);

    this.closeAddExpenseForm();
  }
}
