import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FooterArrendatarioComponent } from './footer-arrendatario.component';

describe('FooterArrendatarioComponent', () => {
  let component: FooterArrendatarioComponent;
  let fixture: ComponentFixture<FooterArrendatarioComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FooterArrendatarioComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(FooterArrendatarioComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
