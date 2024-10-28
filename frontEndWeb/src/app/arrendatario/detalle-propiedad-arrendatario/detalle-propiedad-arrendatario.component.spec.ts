import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DetallePropiedadArrendatarioComponent } from './detalle-propiedad-arrendatario.component';

describe('DetallePropiedadArrendatarioComponent', () => {
  let component: DetallePropiedadArrendatarioComponent;
  let fixture: ComponentFixture<DetallePropiedadArrendatarioComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DetallePropiedadArrendatarioComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(DetallePropiedadArrendatarioComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
