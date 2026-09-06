import { Component, EventEmitter, Output } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { CreateIncomeRequest } from '../utilities/models/request/create-income-request';

@Component({
  selector: 'app-add-income-form',
  imports: [
    CommonModule,
    ReactiveFormsModule
  ],
  templateUrl: './add-income-form.html',
  styleUrl: './add-income-form.scss',
})
export class AddIncomeForm {
  addIncomeForm!: FormGroup;

  @Output() cancelled = new EventEmitter<void>();
  @Output() incomeSaved = new EventEmitter<CreateIncomeRequest>();

  readonly categories = [
    { value: 'SALARY', label: 'Salary' },
    { value: 'FREELANCE', label: 'Freelance' },
    { value: 'INVESTMENTS', label: 'Investments' },
    { value: 'RENTAL', label: 'Rental' },
    { value: 'SCHOLARSHIP', label: 'Scholarship' }
  ];

  constructor(private fb: FormBuilder) {}

  ngOnInit(): void {
    this.addIncomeForm = this.fb.group({
      name: ['', Validators.required],
      category: ['', Validators.required],
      amount: ['', Validators.required],
      incomeDate: ['', Validators.required],
      description: [''],
    });
  }

  onCancel(): void {
    this.cancelled.emit();
  }

  onSubmit(): void {
    this.addIncomeForm.markAllAsTouched();

    if (this.addIncomeForm.invalid) {
      return;
    }

    const formValue = this.addIncomeForm.getRawValue();

    if (formValue.amount === null) {
      return;
    }

    const income: CreateIncomeRequest = {
      ...formValue,
    };

    this.incomeSaved.emit(income);
  }
}