import { CurrencyPipe } from '@angular/common';
import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-portfolio-overview-component',
  imports: [
    CurrencyPipe
  ],
  templateUrl: './portfolio-overview-component.html',
  styleUrl: './portfolio-overview-component.scss',
})
export class PortfolioOverviewComponent {
  @Input() totalPortfolioValue: number = 0;


}
