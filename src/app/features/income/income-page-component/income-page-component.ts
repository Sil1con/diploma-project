import { Component } from '@angular/core';
import { TotalIncomeComponent } from '../total-income-component/total-income-component';
import { GoalIncomeComponent } from '../goal-income-component/goal-income-component';
import { IncomeSourcesComponent } from '../income-sources-component/income-sources-component';

@Component({
  selector: 'app-income-page-componenet',
  imports: [
    TotalIncomeComponent,
    GoalIncomeComponent,
    IncomeSourcesComponent
  ],
  templateUrl: './income-page-component.html',
  styleUrl: './income-page-component.scss',
})
export class IncomePageComponenet {}
