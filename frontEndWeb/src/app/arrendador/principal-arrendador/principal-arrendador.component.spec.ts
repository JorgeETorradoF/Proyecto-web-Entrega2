import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PrincipalArrendadorComponent } from './principal-arrendador.component';

describe('PrincipalArrendadorComponent', () => {
  let component: PrincipalArrendadorComponent;
  let fixture: ComponentFixture<PrincipalArrendadorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PrincipalArrendadorComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(PrincipalArrendadorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
