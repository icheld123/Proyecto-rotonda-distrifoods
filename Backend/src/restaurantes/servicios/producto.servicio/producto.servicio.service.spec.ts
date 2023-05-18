import { Test, TestingModule } from '@nestjs/testing';
import { ProductoServicioService } from './producto.servicio.service';

describe('ProductoServicioService', () => {
  let service: ProductoServicioService;

  beforeEach(async () => {
    const module: TestingModule = await Test.createTestingModule({
      providers: [ProductoServicioService],
    }).compile();

    service = module.get<ProductoServicioService>(ProductoServicioService);
  });

  it('should be defined', () => {
    expect(service).toBeDefined();
  });
});
