import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PerguntasIndexComponent } from './perguntas-index.component';

describe('PerguntasIndexComponent', () => {
  let component: PerguntasIndexComponent;
  let fixture: ComponentFixture<PerguntasIndexComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PerguntasIndexComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(PerguntasIndexComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
