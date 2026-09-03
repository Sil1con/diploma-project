import { Component, EventEmitter, Output } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { InvestmentType } from '../../utilities/types/investment-type';
import { CreateStockRequest } from '../../utilities/models/requests/create-investment-requests.model';

@Component({
  selector: 'stock-investment-form',
  imports: [ReactiveFormsModule],
  templateUrl: './stock-investment-form.html',
  styleUrl: './stock-investment-form.scss',
})
export class StockInvestmentForm {
  stockForm!: FormGroup;

  @Output() cancelled = new EventEmitter<void>();
  @Output() stockSaved = new EventEmitter<CreateStockRequest>();

  constructor(private fb: FormBuilder) {}

  ngOnInit(): void {
    this.stockForm = this.fb.group({
      type: this.fb.control<InvestmentType>(
        { value: 'STOCK', disabled: true }
      ),
      name: ['', Validators.required],
      ticker: ['', Validators.required],
      quantity: ['', Validators.required],
      pricePerUnit: ['', Validators.required],
      purchaseDate: ['', Validators.required],
      notes: ['']
    });
  }

  onCancel(): void {
    this.cancelled.emit();
  }

  onSubmit(): void {
    this.stockForm.markAllAsTouched();

    if (this.stockForm.invalid) {
      return;
    }

    const formValue = this.stockForm.getRawValue();

    if (formValue.quantity === null) {
      return;
    }

    const stock: CreateStockRequest = {
      ...formValue
    };

    this.stockSaved.emit(stock);
  }
}