import { Test, TestingModule } from '@nestjs/testing';
import { TipoProductoControladorController } from './tipo_producto.controlador.controller';

describe('TipoProductoControladorController', () => {
  let controller: TipoProductoControladorController;

  beforeEach(async () => {
    const module: TestingModule = await Test.createTestingModule({
      controllers: [TipoProductoControladorController],
    }).compile();

    controller = module.get<TipoProductoControladorController>(TipoProductoControladorController);
  });

  it('should be defined', () => {
    expect(controller).toBeDefined();
  });
});
