import { TestBed } from '@angular/core/testing';

import { EdicionAdicionServicioService } from './edicion-adicion.servicio.service';

describe('EdicionAdicionServicioService', () => {
  let service: EdicionAdicionServicioService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(EdicionAdicionServicioService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
