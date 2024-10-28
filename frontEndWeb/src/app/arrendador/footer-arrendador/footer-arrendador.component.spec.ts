import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FooterArrendadorComponent } from './footer-arrendador.component';

describe('FooterArrendadorComponent', () => {
  let component: FooterArrendadorComponent;
  let fixture: ComponentFixture<FooterArrendadorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FooterArrendadorComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FooterArrendadorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
