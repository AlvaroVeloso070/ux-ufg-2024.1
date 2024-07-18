import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RegrasModalComponent } from './regras-modal.component';

describe('RegrasModalComponent', () => {
  let component: RegrasModalComponent;
  let fixture: ComponentFixture<RegrasModalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RegrasModalComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(RegrasModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
