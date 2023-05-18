import { Test, TestingModule } from '@nestjs/testing';
import { RestauranteServicioService } from './restaurante.servicio.service';

describe('RestauranteServicioService', () => {
  let service: RestauranteServicioService;

  beforeEach(async () => {
    const module: TestingModule = await Test.createTestingModule({
      providers: [RestauranteServicioService],
    }).compile();

    service = module.get<RestauranteServicioService>(RestauranteServicioService);
  });

  it('should be defined', () => {
    expect(service).toBeDefined();
  });
});
