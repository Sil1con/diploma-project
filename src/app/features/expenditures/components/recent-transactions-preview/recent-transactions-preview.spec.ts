import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RecentTransactionsPreview } from './recent-transactions-preview';

describe('RecentTransactionsPreview', () => {
  let component: RecentTransactionsPreview;
  let fixture: ComponentFixture<RecentTransactionsPreview>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RecentTransactionsPreview],
    }).compileComponents();

    fixture = TestBed.createComponent(RecentTransactionsPreview);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
