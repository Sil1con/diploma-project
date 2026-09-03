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
import { ChangeDetectorRef } from '@angular/core';
import { map, Observable } from 'rxjs';
import { InvestmentSummary } from '../utilities/models/investment-summary.model';

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
    CashInvestmentForm
  ],
  templateUrl: './investments-page-component.html',
  styleUrl: './investments-page-component.scss',
})
export class InvestmentsPageComponent {
  protected readonly userId: string = '1';
  investmentSelected: InvestmentType = 'STOCK';
  isFormOpened: boolean = false;
  
  investments$!: Observable<Investment[]>;
  previewInvestments$!: Observable<InvestmentSummary[]>;

  portfolioRefreshTrigger = 0;

  constructor(
    private investmentService: InvestmentService,
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit(): void {
    this.refreshInvestmentData();
  }

  handleInvestmentCategory(category: InvestmentType) {
    this.investmentSelected = category;
  }

  handleFormVisibility(isOpened: boolean) {
    this.isFormOpened = isOpened;
  }
  
  handleCancelledForm() {
    this.isFormOpened = false;
  }

  handleSavedForm(investment: CreateInvestmentRequest) {
    investment.userId = this.userId;
    investment.transactionType = 'BUY';

    this.investmentService.createInvestment(investment).subscribe({
      next: () => {
        this.refreshInvestmentData();

        this.portfolioRefreshTrigger++;

        this.handleCancelledForm();
        this.cdr.markForCheck();
      },
      error: (error) => {
        console.error('Failed to create investment', error);
      }
    });
  }

  refreshInvestmentData(): void {
    this.investments$ = this.investmentService.getInvestments(this.userId);
    this.previewInvestments$ = this.investmentService.getPreviewSummary(this.userId);
  }
}