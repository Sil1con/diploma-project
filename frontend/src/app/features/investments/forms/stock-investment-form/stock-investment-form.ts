import { Component, EventEmitter, Output } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { StockInvestment } from '../../models/investments.model';
import { InvestmentType } from '../../types/investment-type';

@Component({
  selector: 'stock-investment-form',
  imports: [ReactiveFormsModule],
  templateUrl: './stock-investment-form.html',
  styleUrl: './stock-investment-form.scss',
})
export class StockInvestmentForm {
  stockForm!: FormGroup;

  @Output() cancelled = new EventEmitter<void>();
  @Output() stockSaved = new EventEmitter<StockInvestment>();

  constructor(private fb: FormBuilder) {}

  ngOnInit(): void {
    this.stockForm = this.fb.group({
      type: this.fb.control<InvestmentType>(
        { value: 'STOCK', disabled: true }
      ),
      investmentName: ['', Validators.required],
      ticker: ['', Validators.required],
      quantity: [Validators.required],
      purchasePrice: [Validators.required],
      brokerAccount: ['', Validators.required],
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

    const stock: StockInvestment = {
      ...formValue,
      type: formValue.type.toLowerCase()
    };

    this.stockSaved.emit(stock);
  }
}