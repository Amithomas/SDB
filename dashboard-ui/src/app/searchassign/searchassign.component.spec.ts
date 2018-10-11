import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchassignComponent } from './searchassign.component';

describe('SearchassignComponent', () => {
  let component: SearchassignComponent;
  let fixture: ComponentFixture<SearchassignComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SearchassignComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SearchassignComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
