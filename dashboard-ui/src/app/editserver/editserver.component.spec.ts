
import { fakeAsync, ComponentFixture, TestBed } from '@angular/core/testing';

import { EditserverComponent } from './editserver.component';

describe('EditserverComponent', () => {
  let component: EditserverComponent;
  let fixture: ComponentFixture<EditserverComponent>;

  beforeEach(fakeAsync(() => {
    TestBed.configureTestingModule({
      declarations: [ EditserverComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EditserverComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  }));

  it('should compile', () => {
    expect(component).toBeTruthy();
  });
});
