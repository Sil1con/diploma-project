import { Component, EventEmitter, Output } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { BondInvestment } from '../../models/investments.model';
import { InvestmentType } from '../../types/investment-type';

@Component({
  selector: 'bond-investment-form',
  imports: [ReactiveFormsModule],
  templateUrl: './bond-investment-form.html',
  styleUrl: './bond-investment-form.scss',
})
export class BondInvestmentForm {
  bondForm!: FormGroup;

  @Output() cancelled = new EventEmitter<void>();
  @Output() bondSaved = new EventEmitter<BondInvestment>();

  constructor(private fb: FormBuilder) {}

  ngOnInit(): void {
    this.bondForm = this.fb.group({
      type: this.fb.control<InvestmentType>({
        value: 'BOND',
        disabled: true
      }),

      investmentName: ['', Validators.required],
      issuer: ['', Validators.required],
      isin: ['', Validators.required],

      faceValue: [
        null,
        [Validators.required, Validators.min(0.01)]
      ],

      couponRate: [
        null,
        [Validators.required, Validators.min(0)]
      ],

      maturityDate: ['', Validators.required],

      quantity: [
        null,
        [Validators.required, Validators.min(0.01)]
      ],

      purchasePrice: [
        null,
        [Validators.required, Validators.min(0.01)]
      ],

      purchaseDate: ['', Validators.required],
      notes: ['']
    });
  }

  onCancel(): void {
    this.cancelled.emit();
  }

  onSubmit(): void {
    this.bondForm.markAllAsTouched();

    if (this.bondForm.invalid) {
      return;
    }

    const formValue = this.bondForm.getRawValue();

    if (formValue.amount === null) {
      return;
    }

    const commodity: BondInvestment = {
      ...formValue,
      type: formValue.type.toLowerCase(),
    };

    this.bondSaved.emit(commodity);
  }
}
