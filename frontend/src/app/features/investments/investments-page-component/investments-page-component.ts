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
import { Investment } from '../models/investments.model';
import { InvestmentType } from '../types/investment-type';
import investmentsJSON from '../../../data/investments.json';

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
  investmentSelected: InvestmentType = 'STOCK';
  isFormOpened: boolean = false;
  
  investments: Investment[] = [];

  ngOnInit(): void {
    this.investments = investmentsJSON.investments as Investment[];;
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

  handleSavedForm(investment: Investment) {
    this.investments = [investment, ...this.investments];
    console.log(this.investments);
    this.handleCancelledForm();
  }

  calculatePortfolioTotalValue(): number {
    let totalSum = 0;

    this.investments.forEach((investment) => {
      let totalInvestment = investment.purchasePrice * investment.quantity;
      totalSum += totalInvestment;
    })
    
    return totalSum;
  }
}