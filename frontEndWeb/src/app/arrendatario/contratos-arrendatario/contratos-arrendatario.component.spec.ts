import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ContratosArrendatarioComponent } from './contratos-arrendatario.component';

describe('ContratosArrendatarioComponent', () => {
  let component: ContratosArrendatarioComponent;
  let fixture: ComponentFixture<ContratosArrendatarioComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ContratosArrendatarioComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ContratosArrendatarioComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
