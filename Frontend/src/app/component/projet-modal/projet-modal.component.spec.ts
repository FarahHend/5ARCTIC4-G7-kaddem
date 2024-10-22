import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProjetModalComponent } from './projet-modal.component';

describe('ProjetModalComponent', () => {
  let component: ProjetModalComponent;
  let fixture: ComponentFixture<ProjetModalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ProjetModalComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProjetModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
