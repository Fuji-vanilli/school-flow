import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ClassConcilComponent } from './class-concil.component';

describe('ClassConcilComponent', () => {
  let component: ClassConcilComponent;
  let fixture: ComponentFixture<ClassConcilComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ClassConcilComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ClassConcilComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
