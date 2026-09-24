import { AsyncPipe, CurrencyPipe } from '@angular/common';
import { Component, Input, OnChanges, SimpleChanges } from '@angular/core';
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
export class PortfolioOverviewComponent {
  protected readonly userId: string = '1';

  @Input() portfolioTotalValue$!: Observable<number>;
}
