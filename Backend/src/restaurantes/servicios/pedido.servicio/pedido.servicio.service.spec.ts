import { Test, TestingModule } from '@nestjs/testing';
import { PedidoServicioService } from './pedido.servicio.service';

describe('PedidoServicioService', () => {
  let service: PedidoServicioService;

  beforeEach(async () => {
    const module: TestingModule = await Test.createTestingModule({
      providers: [PedidoServicioService],
    }).compile();

    service = module.get<PedidoServicioService>(PedidoServicioService);
  });

  it('should be defined', () => {
    expect(service).toBeDefined();
  });
});
