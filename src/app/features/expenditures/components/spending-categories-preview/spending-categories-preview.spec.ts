import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SpendingCategoriesPreview } from './spending-categories-preview';

describe('SpendingCategoriesPreview', () => {
  let component: SpendingCategoriesPreview;
  let fixture: ComponentFixture<SpendingCategoriesPreview>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SpendingCategoriesPreview],
    }).compileComponents();

    fixture = TestBed.createComponent(SpendingCategoriesPreview);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
