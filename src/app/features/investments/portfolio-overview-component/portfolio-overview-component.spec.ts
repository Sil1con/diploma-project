import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PortfolioOverviewComponent } from './portfolio-overview-component';

describe('PortfolioOverviewComponent', () => {
  let component: PortfolioOverviewComponent;
  let fixture: ComponentFixture<PortfolioOverviewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PortfolioOverviewComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(PortfolioOverviewComponent);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
