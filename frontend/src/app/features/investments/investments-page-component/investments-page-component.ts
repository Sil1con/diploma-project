import { Component } from '@angular/core';
import { InvestmentsPreviewComponent } from '../investments-preview-component/investments-preview-component';
import { PortfolioOverviewComponent } from '../portfolio-overview-component/portfolio-overview-component';
import { AddInvestmentsPreviewComponent } from '../add-investments-preview-component/add-investments-preview-component';
import { StockInvestmentForm } from '../forms/stock-investment-form/stock-investment-form';
import { EtfInvestmentForm } from '../forms/etf-investment-form/etf-investment-form';
import { CryptoInvestmentForm } from '../forms/crypto-investment-form/crypto-investment-form';
import { CommodityInvestmentForm } from '../forms/commodity-investment-form/commodity-investment-form';
import { BondInvestmentForm } from '../forms/bond-investment-form/bond-investment-form';
import { CashInvestmentForm } from '../forms/cash-investment-form/cash-investment-form';
import { Investment } from '../utilities/models/investments.model';
import { InvestmentType } from '../utilities/types/investment-type';
import { InvestmentService } from '../../../services/investments/investment-service';
import { CreateInvestmentRequest } from '../utilities/models/requests/create-investment-requests.model';
import { BehaviorSubject, map, Observable, reduce } from 'rxjs';
import { InvestmentSummary } from '../utilities/models/investment-summary.model';
import { ViewAllComponent } from '../view-all-component/view-all-component';

@Component({
  selector: 'app-investments-page-component',
  imports: [
    PortfolioOverviewComponent,
    InvestmentsPreviewComponent,
    PortfolioOverviewComponent,
    AddInvestmentsPreviewComponent,
    StockInvestmentForm,
    EtfInvestmentForm,
    CryptoInvestmentForm,
    CommodityInvestmentForm,
    BondInvestmentForm,
    CashInvestmentForm,
    ViewAllComponent
  ],
  templateUrl: './investments-page-component.html',
  styleUrl: './investments-page-component.scss',
})
export class InvestmentsPageComponent {
  protected readonly userId: string = '1';
  investmentSelected: InvestmentType = 'STOCK';
  isFormOpened: boolean = false;
  isViewAllOpened: boolean = false;

  private investments$$ = new BehaviorSubject<Investment[]>([]);
  private investmentSummaries$$ = new BehaviorSubject<InvestmentSummary[]>([]);
  
  investments$: Observable<Investment[]> = this.investments$$.asObservable();
  investmentSummaries$: Observable<InvestmentSummary[]> = this.investmentSummaries$$.asObservable();
  portfolioTotalValue$!: Observable<number>;

  constructor(
    private investmentService: InvestmentService,
  ) {}

  ngOnInit(): void {
    this.refreshInvestmentData();
  }

  handleViewAllVisibility(isOpened: boolean): void {
    this.isViewAllOpened = isOpened;
    
    document.body.style.overflow = isOpened ? 'hidden' : 'auto';
  }

  handleInvestmentCategory(category: InvestmentType) {
    this.investmentSelected = category;
  }

  handleFormVisibility(isOpened: boolean): void {
    this.isFormOpened = isOpened;

    //document.body.style.overflow = isOpened ? 'hidden' : 'auto';
  }

  handleInvestmentSubmitted(investment: CreateInvestmentRequest) {
    investment.userId = this.userId;
    investment.transactionType = 'BUY';

    this.investmentService.createInvestment(investment).subscribe({
      next: () => {
        this.refreshInvestmentData();

        this.handleFormVisibility(false);
      },
      error: (error) => {
        console.error('Failed to create investment', error);
      }
    });
  }

  refreshInvestmentData(): void {
    this.getInvestments();
    this.getInvestmentSummaries();
    this.calculateTotalPortfolio();
  }

  getInvestments() {
    this.investmentService.getInvestments(this.userId)
      .subscribe(investments => 
        this.investments$$.next(investments)
      );
  }

  getInvestmentSummaries() {
    this.investmentService.getPreviewSummary(this.userId)
      .subscribe(investmentSummaries => 
        this.investmentSummaries$$.next(investmentSummaries)
      );
  }

  calculateTotalPortfolio() {
    this.portfolioTotalValue$ = this.investmentSummaries$.pipe(
      map(investments =>
        investments.reduce(
          (total, investment) => total + investment.totalValue,
          0
        )
      )
    );
  }
}