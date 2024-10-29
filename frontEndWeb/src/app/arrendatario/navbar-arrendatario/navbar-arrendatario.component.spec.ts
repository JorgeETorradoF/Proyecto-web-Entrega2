import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NavbarArrendatarioComponent } from './navbar-arrendatario.component';

describe('NavbarArrendatarioComponent', () => {
  let component: NavbarArrendatarioComponent;
  let fixture: ComponentFixture<NavbarArrendatarioComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [NavbarArrendatarioComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(NavbarArrendatarioComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
