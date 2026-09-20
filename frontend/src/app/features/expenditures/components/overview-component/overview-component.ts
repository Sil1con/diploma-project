import { Component, Input, SimpleChanges } from '@angular/core';
import { map, Observable } from 'rxjs';
import { Expenditure } from '../../utilities/models/expenditure';
import { AsyncPipe, CurrencyPipe } from '@angular/common';

@Component({
  selector: 'app-overview-component',
  imports: [
    AsyncPipe,
    CurrencyPipe,
  ],
  templateUrl: './overview-component.html',
  styleUrl: './overview-component.scss',
})
export class OverviewComponent {
  @Input() totalSpent$!: Observable<number>;
  @Input() dailyAverage$!: Observable<number>;
  @Input() transactions$!: Observable<number>;
}
