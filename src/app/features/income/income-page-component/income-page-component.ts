import { Component } from '@angular/core';
import { TotalIncomeComponent } from '../total-income-component/total-income-component';
import { GoalIncomeComponent } from '../goal-income-component/goal-income-component';
import { IncomeSourcesComponent } from '../income-sources-component/income-sources-component';
import { IncomePayload } from '../models/income-payload.model';
import { AddIncomeForm } from '../add-income-form/add-income-form';
import incomesJSON from '../../../data/incomes.json';

@Component({
  selector: 'app-income-page-componenet',
  imports: [
    TotalIncomeComponent,
    GoalIncomeComponent,
    IncomeSourcesComponent,
    AddIncomeForm
  ],
  templateUrl: './income-page-component.html',
  styleUrl: './income-page-component.scss',
})
export class IncomePageComponenet {
  totalIncomesValue: number = 0;
  previousMonthIncome: number = 2500;
  isAddIncomeFormOpened: boolean = false;

  incomeSources: IncomePayload[] = [];

  ngOnInit(): void {
    this.incomeSources = incomesJSON.incomes;
    this.calculateTotalIncomesValue();
  }

  openAddIncomeForm() {
    this.isAddIncomeFormOpened = true;
  }

  closeAddIncomeForm() {
    this.isAddIncomeFormOpened = false;
  }

  handleIncomeSubmitted(income: IncomePayload) {
    this.incomeSources.push(income);

    this.sortIncomeSources();
    this.calculateTotalIncomesValue();

    this.closeAddIncomeForm();
  }

  sortIncomeSources() {
    this.incomeSources = [...this.incomeSources]
      .sort((a, b) => (b.amount) - (a.amount));
  }

  calculateTotalIncomesValue() {
    let totalValue = 0;
    
    this.incomeSources.forEach(income => {
      totalValue += income.amount;
    });

    this.totalIncomesValue = totalValue;
  }
}
