import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UserassigneditComponent } from './userassignedit.component';

describe('UserassigneditComponent', () => {
  let component: UserassigneditComponent;
  let fixture: ComponentFixture<UserassigneditComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UserassigneditComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UserassigneditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
