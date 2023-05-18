import { Test, TestingModule } from '@nestjs/testing';
import { SucursalesServicioService } from './sucursales.servicio.service';

describe('SucursalesServicioService', () => {
  let service: SucursalesServicioService;

  beforeEach(async () => {
    const module: TestingModule = await Test.createTestingModule({
      providers: [SucursalesServicioService],
    }).compile();

    service = module.get<SucursalesServicioService>(SucursalesServicioService);
  });

  it('should be defined', () => {
    expect(service).toBeDefined();
  });
});
