import { Test, TestingModule } from '@nestjs/testing';
import { TipoProductoServicioService } from './tipo_producto.servicio.service';

describe('TipoProductoServicioService', () => {
  let service: TipoProductoServicioService;

  beforeEach(async () => {
    const module: TestingModule = await Test.createTestingModule({
      providers: [TipoProductoServicioService],
    }).compile();

    service = module.get<TipoProductoServicioService>(TipoProductoServicioService);
  });

  it('should be defined', () => {
    expect(service).toBeDefined();
  });
});
