import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InvestmentsPreviewComponent } from './investments-preview-component';

describe('InvestmentsPreviewComponent', () => {
  let component: InvestmentsPreviewComponent;
  let fixture: ComponentFixture<InvestmentsPreviewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [InvestmentsPreviewComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(InvestmentsPreviewComponent);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
