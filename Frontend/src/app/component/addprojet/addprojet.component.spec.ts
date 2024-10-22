import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddprojetComponent } from './addprojet.component';

describe('AddprojetComponent', () => {
  let component: AddprojetComponent;
  let fixture: ComponentFixture<AddprojetComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AddprojetComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddprojetComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
