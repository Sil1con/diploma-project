import { Component, EventEmitter, Output } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';

@Component({
  selector: 'cash-investment-form',
  imports: [ReactiveFormsModule],
  templateUrl: './cash-investment-form.html',
  styleUrl: './cash-investment-form.scss',
})
export class CashInvestmentForm {
  cashForm!: FormGroup;

  @Output() cancelled = new EventEmitter<void>();
  @Output() cashSaved = new EventEmitter<string>();

  constructor(private fb: FormBuilder) {}

  ngOnInit(): void {
    this.cashForm = this.fb.group({
      type: [{ value: 'Cash', disabled: true }],
      amount: ['', Validators.required],
      depositDate: ['', Validators.required],
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

    const cash: string = {
      ...formValue,
      amount: formValue.amount
    };

    this.cashSaved.emit(cash);
  }
}
