import { ComponentFixture, TestBed } from '@angular/core/testing';

import { WikiGroupComponent } from './wiki-group.component';

describe('WikiGroupComponent', () => {
  let component: WikiGroupComponent;
  let fixture: ComponentFixture<WikiGroupComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [WikiGroupComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(WikiGroupComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});