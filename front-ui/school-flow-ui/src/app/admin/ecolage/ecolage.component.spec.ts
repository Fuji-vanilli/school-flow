import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EcolageComponent } from './ecolage.component';

describe('EcolageComponent', () => {
  let component: EcolageComponent;
  let fixture: ComponentFixture<EcolageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [EcolageComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(EcolageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
