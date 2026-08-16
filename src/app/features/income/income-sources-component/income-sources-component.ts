import { Component, Input, Output } from '@angular/core';
import { IncomePayload } from '../models/income-payload.model';
import { CurrencyPipe } from '@angular/common';
import { CapitalLetterPipe } from '../../../shared/pipes/capital-letter-pipe';

@Component({
  selector: 'app-income-sources-component',
  imports: [
    CurrencyPipe,
    CapitalLetterPipe
  ],
  templateUrl: './income-sources-component.html',
  styleUrl: './income-sources-component.scss',
})
export class IncomeSourcesComponent {
  @Input() incomeSources: IncomePayload[] = [];

   incomesIcons: Record<string, string> = {
    salary: 'assets/category_icons/salary.png',
    freelance: 'assets/category_icons/freelance.png',
    investments: 'assets/category_icons/investments.png',
    rental: 'assets/category_icons/rental.png',
    scholarship: 'assets/category_icons/scholarship.png'
  };

  ngOnInit() {
    console.log(this.incomeSources);
  }
}
