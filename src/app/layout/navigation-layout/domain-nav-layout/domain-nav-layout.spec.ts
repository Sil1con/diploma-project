import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DomainNavLayout } from './domain-nav-layout';

describe('DomainNavLayout', () => {
  let component: DomainNavLayout;
  let fixture: ComponentFixture<DomainNavLayout>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DomainNavLayout],
    }).compileComponents();

    fixture = TestBed.createComponent(DomainNavLayout);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
