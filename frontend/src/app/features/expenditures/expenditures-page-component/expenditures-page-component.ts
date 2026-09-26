import { Component } from '@angular/core';
import { OverviewComponent } from '../components/overview-component/overview-component';
import { SpendingCategoriesPreview } from '../components/spending-categories-preview/spending-categories-preview';
import { BudgetSummary } from '../components/budget-summary/budget-summary';
import { RecentExpendituresPreview } from '../components/recent-expenditures-preview/recent-expenditures-preview';
import { AddExpenseForm } from '../components/add-expense-form/add-expense-form';
import { Expenditure } from '../utilities/models/expenditure';
import { ExpenditureService } from '../../../services/expenditures/expenditure-service';
import { BehaviorSubject, map, Observable } from 'rxjs';
import { CategorySummary } from '../utilities/models/category-summary';
import { CreateExpenditureRequest } from '../utilities/models/request/create-expenditure-request';
import { ViewAllComponent } from '../components/view-all-component/view-all-component';

@Component({
  selector: 'app-expenditures-page-component',
  imports: [
    BudgetSummary, 
    OverviewComponent,
    SpendingCategoriesPreview,
    RecentExpendituresPreview,
    AddExpenseForm,
    ViewAllComponent
  ],
  templateUrl: './expenditures-page-component.html',
  styleUrl: './expenditures-page-component.scss',
})
export class ExpendituresPageComponent {
  private readonly userId: string = '1';
  isAddExpenseFormOpened: boolean = false;
  isViewAllOpened: boolean = false;

  private expendituresSubject$$ = new BehaviorSubject<Expenditure[]>([]);
  private categoriesSummarySubject$$ = new BehaviorSubject<CategorySummary[]>([]);

  expenditures$: Observable<Expenditure[]> = this.expendituresSubject$$.asObservable();
  categoriesSummary$: Observable<CategorySummary[]> = this.categoriesSummarySubject$$.asObservable();

  expenditureIcons: Record<string, string> = {
    FOOD: 'assets/category_icons/food.png',
    HOUSING: 'assets/category_icons/housing.png',
    TRANSPORTATION: 'assets/category_icons/transportation.png',
    ENTERTAINMENT: 'assets/category_icons/entertainment.png',
    UTILITIES: 'assets/category_icons/utilities.png',
    HEALTHCARE: 'assets/category_icons/healthcare.png',
    EDUCATION: 'assets/category_icons/education.png',
    SHOPPING: 'assets/category_icons/shopping.png',
    OTHER: 'assets/category_icons/other.png'
  };

  constructor(private expenditureService: ExpenditureService) {}

  ngOnInit() {
    this.refreshExpendituresData();
  }

  openAddExpenseForm(): void {
    this.isAddExpenseFormOpened = true;
  }

  closeAddExpenseForm(): void {
    this.isAddExpenseFormOpened = false;
  }

  handleViewAllVisibility(isOpened: boolean): void {
    this.isViewAllOpened = isOpened;
    
    document.body.style.overflow = isOpened ? 'hidden' : 'auto';
  }

  handleExpenseSubmitted(expenditure: CreateExpenditureRequest) {
    expenditure.userId = this.userId;

    this.expenditureService.createExpenditure(expenditure).subscribe({
      next: () => {
        this.refreshExpendituresData();
        this.closeAddExpenseForm();
      }
    })
  }

  handleExpenditureDeleted(expenditureId: string) {
    this.expenditureService.deleteExpenditure(this.userId, expenditureId).subscribe({
      next: () => {
        this.refreshExpendituresData();
      }
    })
  }

  refreshExpendituresData() {
    this.getCurrentMonthExpenditures();
    this.getCurrentMonthCategoriesSummary();
  }

  getCurrentMonthExpenditures() {
    this.expenditureService.getCurrentMonthExpenditures(this.userId)
      .subscribe(expenditures => 
        this.expendituresSubject$$.next(expenditures)
      );
  }

  getCurrentMonthCategoriesSummary() {
    this.expenditureService.getCurrentMonthCategoriesSummary(this.userId)
      .subscribe(categoriesSummary => 
        this.categoriesSummarySubject$$.next(categoriesSummary)
      );
  }

  calculateTotalSpent(): Observable<number> {
    return this.expenditures$.pipe(
      map(expenditures => 
        expenditures.reduce(
          (total, expenditure) => total + expenditure.amount, 0
        )
      )
    )
  }

  getTransactionsQuantity(): Observable<number> {
    return this.expenditures$.pipe(
      map(expenditures => expenditures.length)
    )
  }

  calculateDailyAverage(period: number): Observable<number> {
    return this.calculateTotalSpent().pipe(
      map(totalSpent =>
        totalSpent / period
      )
    );
  }

  getDaysPassedInCurrentMonth(): number {
    return new Date().getDate();
  }
}