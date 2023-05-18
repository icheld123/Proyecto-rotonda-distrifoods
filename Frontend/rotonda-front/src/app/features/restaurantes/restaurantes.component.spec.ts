import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RestaurantesComponent } from './restaurantes.component';
import { AdicionesComponent } from '../adiciones/adiciones.component';


describe('RestaurantesComponent', () => {
  let component: RestaurantesComponent;
  let fixture: ComponentFixture<RestaurantesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RestaurantesComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RestaurantesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
