import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PrincipalArrendatarioComponent } from './principal-arrendatario.component';

describe('PrincipalArrendatarioComponent', () => {
  let component: PrincipalArrendatarioComponent;
  let fixture: ComponentFixture<PrincipalArrendatarioComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PrincipalArrendatarioComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(PrincipalArrendatarioComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
