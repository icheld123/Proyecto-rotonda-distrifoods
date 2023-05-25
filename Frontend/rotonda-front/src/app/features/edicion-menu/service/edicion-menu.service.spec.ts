import { TestBed } from '@angular/core/testing';

import { EdicionMenuService } from './edicion-menu.service';

describe('RestauranteService', () => {
  let service: EdicionMenuService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(EdicionMenuService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
