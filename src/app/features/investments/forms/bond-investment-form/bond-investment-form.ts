import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';

@Component({
  selector: 'app-bond-investment-form',
  imports: [ReactiveFormsModule],
  templateUrl: './bond-investment-form.html',
  styleUrl: './bond-investment-form.scss',
})
export class BondInvestmentForm {
  bondForm!: FormGroup;

  constructor(private fb: FormBuilder) {}

  ngOnInit(): void {
    this.bondForm = this.fb.group({
      type: [{ value: 'Bond', disabled: true }],
      bondName: ['', Validators.required],
      issuer: ['', Validators.required],
      faceValue: [[Validators.required, Validators.min(0)]],
      purchasePrice: [[Validators.required, Validators.min(0)]],
      couponRate: [[Validators.required, Validators.min(0)]],
      maturityDate: ['', Validators.required],
      purchaseDate: ['', Validators.required],
      notes: ['']
    });
  }
}
