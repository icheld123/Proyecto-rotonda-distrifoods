import { Test, TestingModule } from '@nestjs/testing';
import { DetallePedidoServicioService } from './detalle_pedido.servicio.service';

describe('DetallePedidoServicioService', () => {
  let service: DetallePedidoServicioService;

  beforeEach(async () => {
    const module: TestingModule = await Test.createTestingModule({
      providers: [DetallePedidoServicioService],
    }).compile();

    service = module.get<DetallePedidoServicioService>(DetallePedidoServicioService);
  });

  it('should be defined', () => {
    expect(service).toBeDefined();
  });
});
