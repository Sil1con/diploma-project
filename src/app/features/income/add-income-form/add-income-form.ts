import { Component, EventEmitter, Output } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { IncomePayload } from '../models/income-payload.model';
import { CommonModule } from '@angular/common';

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
  @Output() incomeSaved = new EventEmitter<IncomePayload>();

  readonly categories = [
    { value: 'salary', label: 'Salary' },
    { value: 'freelance', label: 'Freelance' },
    { value: 'investments', label: 'Investments' },
    { value: 'rental', label: 'Rental' },
    { value: 'scholarship', label: 'Scholarship' }
  ];

  constructor(private fb: FormBuilder) {}

  ngOnInit(): void {
    this.addIncomeForm = this.fb.group({
      name: ['', Validators.required],
      category: ['', Validators.required],
      amount: ['', Validators.required],
      startDate: ['', Validators.required],
      notes: [''],
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

    const income: IncomePayload = {
      ...formValue,
      amount: formValue.amount
    };

    console.log(income);

    this.incomeSaved.emit(income);
  }
}