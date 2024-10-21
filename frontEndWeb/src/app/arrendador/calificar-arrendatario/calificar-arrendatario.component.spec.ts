import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CalificarArrendatarioComponent } from './calificar-arrendatario.component';

describe('CalificarArrendatarioComponent', () => {
  let component: CalificarArrendatarioComponent;
  let fixture: ComponentFixture<CalificarArrendatarioComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CalificarArrendatarioComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(CalificarArrendatarioComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
