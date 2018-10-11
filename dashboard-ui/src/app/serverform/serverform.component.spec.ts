import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ServerformComponent } from './serverform.component';

describe('ServerformComponent', () => {
  let component: ServerformComponent;
  let fixture: ComponentFixture<ServerformComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ServerformComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ServerformComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
