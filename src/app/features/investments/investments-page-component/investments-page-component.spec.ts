import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InvestmentsPageComponent } from './investments-page-component';

describe('InvestmentsPageComponent', () => {
  let component: InvestmentsPageComponent;
  let fixture: ComponentFixture<InvestmentsPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [InvestmentsPageComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(InvestmentsPageComponent);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
