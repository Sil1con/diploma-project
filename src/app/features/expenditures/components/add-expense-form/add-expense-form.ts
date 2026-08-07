import { CommonModule } from '@angular/common';
import { Component, EventEmitter, Output } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ExpensePayload } from '../../models/expense-payload.model';

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
  @Output() expenseSaved = new EventEmitter<ExpensePayload>();

  readonly categories = [
    { value: 'housing', label: 'Housing' },
    { value: 'utilities', label: 'Utilities' },
    { value: 'transportation', label: 'Transportation' },
    { value: 'entertainment', label: 'Entertainment' },
    { value: 'health', label: 'Health & Fitness' },
    { value: 'food-dining', label: 'Food & Dining' },
    { value: 'education', label: 'Education' },
    { value: 'other', label: 'Other' }
  ];

  readonly paymentMethods = [
    { value: 'cash', label: 'Cash' },
    { value: 'debit-card', label: 'Debit card' },
    { value: 'credit-card', label: 'Credit card' },
    { value: 'bank-transfer', label: 'Bank transfer' }
  ];

  constructor(private fb: FormBuilder) {}

  ngOnInit(): void {
    this.addExpenseForm = this.fb.group({
      expenseName: ['', Validators.required],
      category: ['', Validators.required],
      amount: ['', Validators.required],
      expenseDate: ['', Validators.required],
      paymentMethod: ['', Validators.required],
      merchant: ['', Validators.required],
      receipt: this.fb.control<File | null>(null),
      notes: [''],
    });
  }

  onCancel(): void {
    this.cancelled.emit();
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

    const expense: ExpensePayload = {
      ...formValue,
      amount: formValue.amount
    };

    console.log(expense);

    this.expenseSaved.emit(expense);
  }
}
