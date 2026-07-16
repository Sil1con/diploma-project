import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DomainNavigation } from './domain-navigation';

describe('DomainNavigation', () => {
  let component: DomainNavigation;
  let fixture: ComponentFixture<DomainNavigation>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DomainNavigation],
    }).compileComponents();

    fixture = TestBed.createComponent(DomainNavigation);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
