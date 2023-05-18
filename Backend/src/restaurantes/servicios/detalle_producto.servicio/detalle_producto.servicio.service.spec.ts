import { Test, TestingModule } from '@nestjs/testing';
import { DetalleProductoServicioService } from './detalle_producto.servicio.service';

describe('DetalleProductoServicioService', () => {
  let service: DetalleProductoServicioService;

  beforeEach(async () => {
    const module: TestingModule = await Test.createTestingModule({
      providers: [DetalleProductoServicioService],
    }).compile();

    service = module.get<DetalleProductoServicioService>(DetalleProductoServicioService);
  });

  it('should be defined', () => {
    expect(service).toBeDefined();
  });
});
