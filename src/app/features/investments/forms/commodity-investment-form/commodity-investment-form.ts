import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';

@Component({
  selector: 'app-commodity-investment-form',
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './commodity-investment-form.html',
  styleUrl: './commodity-investment-form.scss',
})
export class CommodityInvestmentForm {
  commodityForm!: FormGroup;

  constructor(private fb: FormBuilder) {}

  ngOnInit(): void {
    this.commodityForm = this.fb.group({
      type: [{ value: 'Commodity', disabled: true }],
      investmentName: ['', Validators.required],
      symbol: [''],
      initialValue: [Validators.required],
      quantity: [Validators.required],
      purchaseDate: ['', Validators.required],
      commodityPrice: [Validators.required],
      notes: ['']
    });
  }
}
