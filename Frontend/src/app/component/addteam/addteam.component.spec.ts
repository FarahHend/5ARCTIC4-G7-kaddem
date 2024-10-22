import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddteamComponent } from './addteam.component';

describe('AddteamComponent', () => {
  let component: AddteamComponent;
  let fixture: ComponentFixture<AddteamComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AddteamComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddteamComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
