import { Component, EventEmitter, Output } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { InvestmentType } from '../../utilities/types/investment-type';
import { CreateEtfRequest } from '../../utilities/models/requests/create-investment-requests.model';

@Component({
  selector: 'etf-investment-form',
  imports: [ReactiveFormsModule],
  templateUrl: './etf-investment-form.html',
  styleUrl: './etf-investment-form.scss',
})
export class EtfInvestmentForm {
  etfForm!: FormGroup;

  @Output() cancelled = new EventEmitter<void>();
  @Output() etfSaved = new EventEmitter<CreateEtfRequest>();

  constructor(private fb: FormBuilder) {}

  ngOnInit(): void {
    this.etfForm = this.fb.group({
      type: this.fb.control<InvestmentType>(
        { value: 'ETF', disabled: true }
      ),
      name: ['', Validators.required],
      ticker: ['', Validators.required],
      quantity: ['', Validators.required],
      pricePerUnit: ['', Validators.required],
      purchaseDate: ['', Validators.required],
      brokerAccount: ['', Validators.required],
      notes: ['']
    });
  }

  onCancel(): void {
    this.cancelled.emit();
  }

  onSubmit(): void {
    this.etfForm.markAllAsTouched();

    if (this.etfForm.invalid) {
      return;
    }

    const formValue = this.etfForm.getRawValue();

    if (formValue.quantity === null) {
      return;
    }

    const etf: CreateEtfRequest = {
      ...formValue
    };

    this.etfSaved.emit(etf);
  }
}