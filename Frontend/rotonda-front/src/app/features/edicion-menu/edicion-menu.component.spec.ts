import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EdicionMenuComponent } from './edicion-menu.component';

describe('EdicionMenuComponent', () => {
  let component: EdicionMenuComponent;
  let fixture: ComponentFixture<EdicionMenuComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EdicionMenuComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EdicionMenuComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
