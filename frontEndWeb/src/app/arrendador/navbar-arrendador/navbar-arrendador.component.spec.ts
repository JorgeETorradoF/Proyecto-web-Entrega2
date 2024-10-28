import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NavbarArrendadorComponent } from './navbar-arrendador.component';

describe('NavbarArrendadorComponent', () => {
  let component: NavbarArrendadorComponent;
  let fixture: ComponentFixture<NavbarArrendadorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [NavbarArrendadorComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(NavbarArrendadorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
