import { Test, TestingModule } from '@nestjs/testing';
import { AdicionServicioService } from './adicion.servicio.service';

describe('AdicionServicioService', () => {
  let service: AdicionServicioService;

  beforeEach(async () => {
    const module: TestingModule = await Test.createTestingModule({
      providers: [AdicionServicioService],
    }).compile();

    service = module.get<AdicionServicioService>(AdicionServicioService);
  });

  it('should be defined', () => {
    expect(service).toBeDefined();
  });
});
