import { Component, EventEmitter, Output } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';

import { InvestmentType } from '../../utilities/types/investment-type';
import { CreateCryptoRequest } from '../../utilities/models/requests/create-investment-requests.model';

@Component({
  selector: 'crypto-investment-form',
  imports: [ReactiveFormsModule],
  templateUrl: './crypto-investment-form.html',
  styleUrl: './crypto-investment-form.scss',
})
export class CryptoInvestmentForm {
  cryptoForm!: FormGroup;

  @Output() cancelled = new EventEmitter<void>();
  @Output() cryptoSaved = new EventEmitter<CreateCryptoRequest>();

  constructor(private fb: FormBuilder) {}

  ngOnInit(): void {
    this.cryptoForm = this.fb.group({
      type: this.fb.control<InvestmentType>(
        { value: 'CRYPTO', disabled: true }
      ),
      name: ['', Validators.required],
      symbol: ['', Validators.required],
      quantity: ['', Validators.required],
      pricePerUnit: ['', Validators.required],
      purchaseDate: ['', Validators.required],
      wallet: ['', Validators.required],
      notes: ['']
    });
  }

  onCancel(): void {
    this.cancelled.emit();
  }

  onSubmit(): void {
    this.cryptoForm.markAllAsTouched();

    if (this.cryptoForm.invalid) {
      return;
    }

    const formValue = this.cryptoForm.getRawValue();

    if (formValue.quantity === null) {
      return;
    }

    const crypto: CreateCryptoRequest = {
      ...formValue
    };

    this.cryptoSaved.emit(crypto);
  }
}
