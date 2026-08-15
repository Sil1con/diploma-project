import { Component, EventEmitter, Output } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { CryptoInvestment } from '../../models/investments.model';

@Component({
  selector: 'crypto-investment-form',
  imports: [ReactiveFormsModule],
  templateUrl: './crypto-investment-form.html',
  styleUrl: './crypto-investment-form.scss',
})
export class CryptoInvestmentForm {
  cryptoForm!: FormGroup;

  @Output() cancelled = new EventEmitter<void>();
  @Output() cryptoSaved = new EventEmitter<CryptoInvestment>();

  constructor(private fb: FormBuilder) {}

  ngOnInit(): void {
    this.cryptoForm = this.fb.group({
      type: [{ value: 'crypto', disabled: true }],
      investmentName: ['', Validators.required],
      symbol: ['', Validators.required],
      quantity: ['', Validators.required],
      purchasePrice: ['', Validators.required],
      purchaseDate: ['', Validators.required],
      assetHoldingPlace: ['', Validators.required],
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

    if (formValue.amount === null) {
      return;
    }

    const crypto: CryptoInvestment = {
      ...formValue,
      type: formValue.type.toLowerCase()
    };

    this.cryptoSaved.emit(crypto);
  }
}
