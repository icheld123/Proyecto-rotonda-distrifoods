import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EdicionAdicionComponent } from './edicion-adicion.component';

describe('EdicionAdicionComponent', () => {
  let component: EdicionAdicionComponent;
  let fixture: ComponentFixture<EdicionAdicionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EdicionAdicionComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EdicionAdicionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
