import { Test, TestingModule } from '@nestjs/testing';
import { DetalleProductoControladorController } from './detalle_producto.controlador.controller';

describe('DetalleProductoControladorController', () => {
  let controller: DetalleProductoControladorController;

  beforeEach(async () => {
    const module: TestingModule = await Test.createTestingModule({
      controllers: [DetalleProductoControladorController],
    }).compile();

    controller = module.get<DetalleProductoControladorController>(DetalleProductoControladorController);
  });

  it('should be defined', () => {
    expect(controller).toBeDefined();
  });
});
