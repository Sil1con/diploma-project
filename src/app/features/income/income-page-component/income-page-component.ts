import { Component } from '@angular/core';
import { TotalIncomeComponent } from '../total-income-component/total-income-component';
import { GoalIncomeComponent } from '../goal-income-component/goal-income-component';
import { IncomeSourcesComponent } from '../income-sources-component/income-sources-component';
import { IncomePayload } from '../models/income-paylod.model';
import { AddIncomeForm } from '../add-income-form/add-income-form';

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
  isAddIncomeFormOpened: boolean = false;

  incomeSources: IncomePayload[] = [];

  openAddIncomeForm() {
    this.isAddIncomeFormOpened = true;
  }

  closeAddIncomeForm() {
    this.isAddIncomeFormOpened = false;
  }

  handleIncomeSubmitted(income: IncomePayload) {
    this.incomeSources = [income, ...this.incomeSources];

    this.closeAddIncomeForm();
  }
}
