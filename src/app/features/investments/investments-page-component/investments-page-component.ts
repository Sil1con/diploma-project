import { Component } from '@angular/core';
import { InvestmentsPreviewComponent } from '../investments-preview-component/investments-preview-component';
import { PortfolioOverviewComponent } from '../portfolio-overview-component/portfolio-overview-component';
import { AddInvestmentsPreviewComponent } from '../add-investments-preview-component/add-investments-preview-component';

@Component({
  selector: 'app-investments-page-component',
  imports: [
    PortfolioOverviewComponent,
    InvestmentsPreviewComponent,
    PortfolioOverviewComponent,
    AddInvestmentsPreviewComponent
  ],
  templateUrl: './investments-page-component.html',
  styleUrl: './investments-page-component.scss',
})
export class InvestmentsPageComponent {}
