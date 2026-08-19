import { Component, EventEmitter, Output } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { CashInvestment, InvestmentType } from '../../models/investments.model';

@Component({
  selector: 'cash-investment-form',
  imports: [ReactiveFormsModule],
  templateUrl: './cash-investment-form.html',
  styleUrl: './cash-investment-form.scss',
})
export class CashInvestmentForm {
  cashForm!: FormGroup;

  @Output() cancelled = new EventEmitter<void>();
  @Output() cashSaved = new EventEmitter<CashInvestment>();


  constructor(private fb: FormBuilder) {}

  ngOnInit(): void {
    this.cashForm = this.fb.group({
      type: this.fb.control<InvestmentType>(
        { value: 'CASH', disabled: true }
      ),
      quantity: ['', Validators.required],
      purchaseDate: ['', Validators.required],
      notes: ['']
    });
  }

  onCancel(): void {
    this.cancelled.emit();
  }

  onSubmit(): void {
    this.cashForm.markAllAsTouched();

    if (this.cashForm.invalid) {
      return;
    }

    const formValue = this.cashForm.getRawValue();

    if (formValue.amount === null) {
      return;
    }

    const cash: CashInvestment = {
      ...formValue,
      investmentName: formValue.type,
      purchasePrice: 1,
      type: formValue.type.toLowerCase(),
    };

    console.log(cash);

    this.cashSaved.emit(cash);
  }
}
