import { Component, EventEmitter, Output } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { InvestmentType } from '../../utilities/types/investment-type';
import { CreateCashRequest } from '../../utilities/models/requests/create-investment-requests.model';

@Component({
  selector: 'cash-investment-form',
  imports: [ReactiveFormsModule],
  templateUrl: './cash-investment-form.html',
  styleUrl: './cash-investment-form.scss',
})
export class CashInvestmentForm {
  cashForm!: FormGroup;

  @Output() cancelled = new EventEmitter<void>();
  @Output() cashSaved = new EventEmitter<CreateCashRequest>();


  constructor(private fb: FormBuilder) {}

  ngOnInit(): void {
    this.cashForm = this.fb.group({
      type: this.fb.control<InvestmentType>(
        { value: 'CASH', disabled: true }
      ),
      name: ['', Validators.required],
      quantity: ['', Validators.required],
      pricePerUnit: [{ value: '1', disabled: true }, Validators.required],
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

    if (formValue.quantity === null) {
      return;
    }

    const cash: CreateCashRequest = {
      ...formValue,
      currency: 'EUR'
    };

    console.log(cash);

    this.cashSaved.emit(cash);
  }
}
