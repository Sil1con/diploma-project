import { Component, EventEmitter, Output } from '@angular/core';
import { InvestmentType } from '../types/investment-type';

@Component({
  selector: 'app-add-investments-preview-component',
  imports: [],
  templateUrl: './add-investments-preview-component.html',
  styleUrl: './add-investments-preview-component.scss',
})
export class AddInvestmentsPreviewComponent {
  selectedCategory: InvestmentType = 'STOCK';
  isAddFormOpened: boolean = false;

  @Output() selectedEvent = new EventEmitter<InvestmentType>();
  @Output() formOpened = new EventEmitter<boolean>();

  selectCategory(category: InvestmentType): void {
    this.selectedCategory = category;
    this.selectedEvent.emit(category);
  }

  openForm() {
    this.isAddFormOpened = true;
    this.formOpened.emit(this.isAddFormOpened);
  }
  
  closeForm() {
    this.isAddFormOpened = false;
    this.formOpened.emit(this.isAddFormOpened);
  }
}
