import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RecentExpendituresPreview } from './recent-expenditures-preview';

describe('RecentExpendituresPreview', () => {
  let component: RecentExpendituresPreview;
  let fixture: ComponentFixture<RecentExpendituresPreview>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RecentExpendituresPreview],
    }).compileComponents();

    fixture = TestBed.createComponent(RecentExpendituresPreview);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
