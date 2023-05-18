import { Test, TestingModule } from '@nestjs/testing';
import { DetallePedidoControladorController } from './detalle_pedido.controlador.controller';

describe('DetallePedidoControladorController', () => {
  let controller: DetallePedidoControladorController;

  beforeEach(async () => {
    const module: TestingModule = await Test.createTestingModule({
      controllers: [DetallePedidoControladorController],
    }).compile();

    controller = module.get<DetallePedidoControladorController>(DetallePedidoControladorController);
  });

  it('should be defined', () => {
    expect(controller).toBeDefined();
  });
});
