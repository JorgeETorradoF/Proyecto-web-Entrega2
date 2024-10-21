import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ContratosArrendadorComponent } from './contratos-arrendador.component';

describe('ContratosArrendadorComponent', () => {
  let component: ContratosArrendadorComponent;
  let fixture: ComponentFixture<ContratosArrendadorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ContratosArrendadorComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ContratosArrendadorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
