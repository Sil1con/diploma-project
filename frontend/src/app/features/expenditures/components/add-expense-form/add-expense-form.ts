import { CommonModule } from '@angular/common';
import { Component, EventEmitter, Output } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { CreateExpenditureRequest } from '../../utilities/models/request/create-expenditure-request';

@Component({
  selector: 'app-add-expense-form',
  imports: [
    CommonModule,
    ReactiveFormsModule
  ],
  templateUrl: './add-expense-form.html',
  styleUrl: './add-expense-form.scss',
})
export class AddExpenseForm {
  addExpenseForm!: FormGroup;

  @Output() cancelled = new EventEmitter<void>();
  @Output() expenseSaved = new EventEmitter<CreateExpenditureRequest>();

  readonly categories = [
    { value: 'FOOD', label: 'Food' },
    { value: 'HOUSING', label: 'Housing' },
    { value: 'TRANSPORTATION', label: 'Transportation' },
    { value: 'ENTERTAINMENT', label: 'Entertainment' },
    { value: 'UTILITIES', label: 'Utilities' },
    { value: 'HEALTHCARE', label: 'Healthcare' },
    { value: 'EDUCATION', label: 'Education' },
    { value: 'SHOPPING', label: 'Shopping' },
    { value: 'OTHER', label: 'Other' }
  ];

  readonly paymentMethods = [
    { value: 'CASH', label: 'Cash' },
    { value: 'DEBIT_CARD', label: 'Debit card' },
    { value: 'CREDIT_CARD', label: 'Credit card' },
    { value: 'BANK_TRANSFER', label: 'Bank transfer' }
  ];

  constructor(private fb: FormBuilder) {}

  ngOnInit(): void {
    this.addExpenseForm = this.fb.group({
      name: ['', Validators.required],
      category: ['', Validators.required],
      amount: ['', Validators.required],
      expenditureDate: ['', Validators.required],
      paymentMethod: ['', Validators.required],
      vendor: ['', Validators.required],
      receipt: this.fb.control<File | null>(null),
      notes: [''],
    });
  }

  onCancel(): void {
    this.cancelled.emit();
  }

  onReceiptSelected(event: Event): File | null {
    const input = event.target as HTMLInputElement;
    const file = input.files?.[0] ?? null;

    return file;
  }

  onSubmit(): void {
    this.addExpenseForm.markAllAsTouched();

    if (this.addExpenseForm.invalid) {
      return;
    }

    const formValue = this.addExpenseForm.getRawValue();

    if (formValue.amount === null) {
      return;
    }

    const expenditure: CreateExpenditureRequest = {
      ...formValue
    };

    this.expenseSaved.emit(expenditure);
  }
}
