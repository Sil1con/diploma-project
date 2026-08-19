import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddInvestmentsPreviewComponent } from './add-investments-preview-component';

describe('AddInvestmentsPreviewComponent', () => {
  let component: AddInvestmentsPreviewComponent;
  let fixture: ComponentFixture<AddInvestmentsPreviewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AddInvestmentsPreviewComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(AddInvestmentsPreviewComponent);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
