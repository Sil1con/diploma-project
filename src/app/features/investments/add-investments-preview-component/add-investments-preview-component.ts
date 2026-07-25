import { Component } from '@angular/core';

@Component({
  selector: 'app-add-investments-preview-component',
  imports: [],
  templateUrl: './add-investments-preview-component.html',
  styleUrl: './add-investments-preview-component.scss',
})
export class AddInvestmentsPreviewComponent {
  selectedCategory: string | null = null;

  selectCategory(category: string): void {
    this.selectedCategory = category;
  }
}
