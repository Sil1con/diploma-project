import { AsyncPipe, CurrencyPipe } from '@angular/common';
import { Component, Input, OnChanges, SimpleChanges } from '@angular/core';
import { InvestmentService } from '../../../services/investments/investment-service';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-portfolio-overview-component',
  imports: [
    AsyncPipe,
    CurrencyPipe
  ],
  templateUrl: './portfolio-overview-component.html',
  styleUrl: './portfolio-overview-component.scss',
})
export class PortfolioOverviewComponent implements OnChanges {
  protected readonly userId: string = '1';

  totalPortfolioValue$!: Observable<number>;

  @Input() refreshTrigger!: number;

  constructor(private investmentService: InvestmentService) {}

  ngOnInit() {
    this.getPortfolioTotalValue();
  }

  ngOnChanges(changes: SimpleChanges): void {
    if (changes['refreshTrigger']) {
      this.getPortfolioTotalValue();
    }
  }

  getPortfolioTotalValue(): void {
    this.totalPortfolioValue$ = this.investmentService.getPortfolioTotalValue(this.userId);
  }
}
