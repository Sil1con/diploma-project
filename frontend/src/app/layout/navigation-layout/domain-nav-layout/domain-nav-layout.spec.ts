import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DomainNavigationLayout } from './domain-nav-layout';

describe('DomainNavLayout', () => {
  let component: DomainNavigationLayout;
  let fixture: ComponentFixture<DomainNavigationLayout>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DomainNavigationLayout],
    }).compileComponents();

    fixture = TestBed.createComponent(DomainNavigationLayout);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
