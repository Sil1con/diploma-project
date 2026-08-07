import { Component, EventEmitter, Output } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';

@Component({
  selector: 'stock-investment-form',
  imports: [ReactiveFormsModule],
  templateUrl: './stock-investment-form.html',
  styleUrl: './stock-investment-form.scss',
})
export class StockInvestmentForm {
  stockForm!: FormGroup;

  @Output() cancelled = new EventEmitter<void>();
  @Output() stockSaved = new EventEmitter<string>();

  constructor(private fb: FormBuilder) {}

  ngOnInit(): void {
    this.stockForm = this.fb.group({
      type: [{ value: 'Stock', disabled: true }],
      stockName: ['', Validators.required],
      ticker: ['', Validators.required],
      quantity: ['', Validators.required],
      purchasePrice: ['', Validators.required],
      purchaseDate: ['', Validators.required],
      brokerAccount: ['', Validators.required],
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

    if (formValue.amount === null) {
      return;
    }

    const income: string = {
      ...formValue,
      amount: formValue.amount
    };

    this.stockSaved.emit(income);
  }
}