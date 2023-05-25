import { TestBed } from '@angular/core/testing';

import { MenuServicioService } from './menu.servicio.service';

describe('MenuServicioService', () => {
  let service: MenuServicioService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(MenuServicioService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
