import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdicionesComponent } from './adiciones.component';

describe('AdicionesComponent', () => {
  let component: AdicionesComponent;
  let fixture: ComponentFixture<AdicionesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdicionesComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdicionesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
