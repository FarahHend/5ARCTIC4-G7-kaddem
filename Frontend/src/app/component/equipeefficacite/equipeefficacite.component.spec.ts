import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EquipeefficaciteComponent } from './equipeefficacite.component';

describe('EquipeefficaciteComponent', () => {
  let component: EquipeefficaciteComponent;
  let fixture: ComponentFixture<EquipeefficaciteComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EquipeefficaciteComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EquipeefficaciteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
