import { ChangeDetectorRef, Component } from '@angular/core';
import { TotalIncomeComponent } from '../total-income-component/total-income-component';
import { GoalIncomeComponent } from '../goal-income-component/goal-income-component';
import { IncomeSourcesComponent } from '../income-sources-component/income-sources-component';
import { IncomeSource } from '../utilities/models/income-source';
import { AddIncomeForm } from '../add-income-form/add-income-form';
import { BehaviorSubject, Observable, of } from 'rxjs';
import { IncomeService } from '../../../services/income/income-service';
import { CreateIncomeRequest } from '../utilities/models/request/create-income-request';

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
  private readonly userId: string = '1';

  incomeGoal: number = 10000;
  previousMonthIncome: number = 1300;
  isAddIncomeFormOpened: boolean = false;
  
  private totalIncomeValueSubject$$ = new BehaviorSubject<number>(0);
  private incomeSources$$ = new BehaviorSubject<IncomeSource[]>([]);

  totalIncomeValue$: Observable<number> = this.totalIncomeValueSubject$$.asObservable();
  incomeSources$: Observable<IncomeSource[]> = this.incomeSources$$.asObservable();

  constructor(
    private incomeService: IncomeService,
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit(): void {
    this.refreshIncomeData();
  }

  openAddIncomeForm() {
    this.isAddIncomeFormOpened = true;
  }

  closeAddIncomeForm() {
    this.isAddIncomeFormOpened = false;
  }

  handleIncomeSubmitted(income: CreateIncomeRequest) {
    income.userId = this.userId;

    this.incomeService.createIncomeSource(income).subscribe({
      next: (incomeResponse) => {
        console.log(incomeResponse);
        
        this.refreshIncomeData();
        this.closeAddIncomeForm();
        this.cdr.markForCheck();
      },
      error: (error) => {
        console.error('Failed to create income source', error);
      }
    })
  }

  refreshIncomeData() {
    this.getCurrentMonthIncomeSources();
    this.getCurrentMonthTotalIncome();
  }

  getCurrentMonthIncomeSources() {
    this.incomeService.getCurrentMonthIncomeSources(this.userId)
    .subscribe(incomeSources => {
      this.incomeSources$$.next(incomeSources);
    })
  }

  getCurrentMonthTotalIncome() {
    this.incomeService.getCurrentMonthTotalIncome(this.userId)
    .subscribe(totalIncome => {
      this.totalIncomeValueSubject$$.next(totalIncome);
    })
  }
}