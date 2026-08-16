import { CurrencyPipe } from '@angular/common';
import { Component, Input } from '@angular/core';
import { CustomPercentPipe } from '../../../shared/pipes/percent-pipe';

@Component({
  selector: 'app-total-income-component',
  imports: [
    CurrencyPipe,
    CustomPercentPipe
  ],
  templateUrl: './total-income-component.html',
  styleUrl: './total-income-component.scss',
})
export class TotalIncomeComponent {
  @Input() totalIncomeSourcesValue: number = 0;
  @Input() previousMonthIncome: number = 0;
  prevMonthDifference: number = 0;

  ngOnInit() {
    this.calculatePrevMonthDifference();  
  }

  ngOnChanges() {
    this.calculatePrevMonthDifference();
  }

  calculatePrevMonthDifference() {
    let difference = (((this.totalIncomeSourcesValue / this.previousMonthIncome) * 100) - 100);

    this.prevMonthDifference = difference;
  }
}
