import { Test, TestingModule } from '@nestjs/testing';
import { PedidoControladorController } from './pedido.controlador.controller';

describe('PedidoControladorController', () => {
  let controller: PedidoControladorController;

  beforeEach(async () => {
    const module: TestingModule = await Test.createTestingModule({
      controllers: [PedidoControladorController],
    }).compile();

    controller = module.get<PedidoControladorController>(PedidoControladorController);
  });

  it('should be defined', () => {
    expect(controller).toBeDefined();
  });
});
