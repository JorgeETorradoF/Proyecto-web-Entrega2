import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CalificarArrendadorComponent } from './calificar-arrendador.component';

describe('CalificarArrendadorComponent', () => {
  let component: CalificarArrendadorComponent;
  let fixture: ComponentFixture<CalificarArrendadorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CalificarArrendadorComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CalificarArrendadorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
