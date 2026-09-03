import { Component, EventEmitter, Output } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { InvestmentType } from '../../utilities/types/investment-type';
import { CreateBondRequest } from '../../utilities/models/requests/create-investment-requests.model';

@Component({
  selector: 'bond-investment-form',
  imports: [ReactiveFormsModule],
  templateUrl: './bond-investment-form.html',
  styleUrl: './bond-investment-form.scss',
})
export class BondInvestmentForm {
  bondForm!: FormGroup;

  @Output() cancelled = new EventEmitter<void>();
  @Output() bondSaved = new EventEmitter<CreateBondRequest>();

  constructor(private fb: FormBuilder) {}

  ngOnInit(): void {
    this.bondForm = this.fb.group({
      type: this.fb.control<InvestmentType>({
        value: 'BOND',
        disabled: true
      }),
      name: ['', Validators.required],
      issuer: ['', Validators.required],
      isin: ['', Validators.required],
      faceValue: ['', [Validators.required, Validators.min(0.01)]],
      couponRate: ['', [Validators.required, Validators.min(0)]],
      maturityDate: ['', Validators.required],
      quantity: ['', [Validators.required, Validators.min(0.01)]],
      pricePerUnit: ['', [Validators.required, Validators.min(0.01)]],
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

    if (formValue.quantity === null) {
      return;
    }

    const commodity: CreateBondRequest = {
      ...formValue,
    };

    this.bondSaved.emit(commodity);
  }
}
