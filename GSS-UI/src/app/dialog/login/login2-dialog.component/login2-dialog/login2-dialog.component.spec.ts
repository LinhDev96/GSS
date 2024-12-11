import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Login2DialogComponent } from './login2-dialog.component';

describe('Login2DialogComponent', () => {
  let component: Login2DialogComponent;
  let fixture: ComponentFixture<Login2DialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [Login2DialogComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(Login2DialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
