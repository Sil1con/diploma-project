import { Component, EventEmitter, Output } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { CommodityInvestment } from '../../models/investments.model';
import { InvestmentType } from '../../types/investment-type';

@Component({
  selector: 'commodity-investment-form',
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './commodity-investment-form.html',
  styleUrl: './commodity-investment-form.scss',
})
export class CommodityInvestmentForm {
  commodityForm!: FormGroup;

  @Output() cancelled = new EventEmitter<void>();
  @Output() commoditySaved = new EventEmitter<CommodityInvestment>();

  constructor(private fb: FormBuilder) {}

  ngOnInit(): void {
    this.commodityForm = this.fb.group({
      type: this.fb.control<InvestmentType>(
        { value: 'COMMODITY', disabled: true }
      ),
      investmentName: ['', Validators.required],
      ticker: ['', Validators.required],
      initialValue: ['', Validators.required],
      quantity: ['', Validators.required],
      purchaseDate: ['', Validators.required],
      commodityPrice: ['', Validators.required],
      notes: ['']
    });
  }
  
  onCancel(): void {
    this.cancelled.emit();
  }

  onSubmit(): void {
    this.commodityForm.markAllAsTouched();

    if (this.commodityForm.invalid) {
      return;
    }

    const formValue = this.commodityForm.getRawValue();

    if (formValue.amount === null) {
      return;
    }

    const commodity: CommodityInvestment = {
      ...formValue,
      type: formValue.type.toLowerCase(),
    };

    this.commoditySaved.emit(commodity);
  }
}
