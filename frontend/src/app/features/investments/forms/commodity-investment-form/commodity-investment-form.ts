import { Component, EventEmitter, Output } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { InvestmentType } from '../../utilities/types/investment-type';
import { CreateCommodityRequest } from '../../utilities/models/requests/create-investment-requests.model';

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
  @Output() commoditySaved = new EventEmitter<CreateCommodityRequest>();

  constructor(private fb: FormBuilder) {}

  ngOnInit(): void {
    this.commodityForm = this.fb.group({
      type: this.fb.control<InvestmentType>(
        { value: 'COMMODITY', disabled: true }
      ),
      name: ['', Validators.required],
      symbol: ['', Validators.required],
      quantity: ['', Validators.required],
      purchaseDate: ['', Validators.required],
      pricePerUnit: ['', Validators.required],
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

    if (formValue.quantity === null) {
      return;
    }

    const commodity: CreateCommodityRequest = {
      ...formValue
    };

    this.commoditySaved.emit(commodity);
  }
}
