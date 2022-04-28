import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UploaddComponent } from './uploadd.component';

describe('UploaddComponent', () => {
  let component: UploaddComponent;
  let fixture: ComponentFixture<UploaddComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UploaddComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UploaddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
