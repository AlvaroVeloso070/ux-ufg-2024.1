import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RankingIndexComponent } from './ranking-index.component';

describe('RankingIndexComponent', () => {
  let component: RankingIndexComponent;
  let fixture: ComponentFixture<RankingIndexComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RankingIndexComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(RankingIndexComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
