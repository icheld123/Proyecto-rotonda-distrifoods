import { Test, TestingModule } from '@nestjs/testing';
import { MetodosPagoServicioService } from './metodos_pago.servicio.service';

describe('MetodosPagoServicioService', () => {
  let service: MetodosPagoServicioService;

  beforeEach(async () => {
    const module: TestingModule = await Test.createTestingModule({
      providers: [MetodosPagoServicioService],
    }).compile();

    service = module.get<MetodosPagoServicioService>(MetodosPagoServicioService);
  });

  it('should be defined', () => {
    expect(service).toBeDefined();
  });
});
