import { Test, TestingModule } from '@nestjs/testing';
import { ProductoControladorController } from './producto.controlador.controller';

describe('ProductoControladorController', () => {
  let controller: ProductoControladorController;

  beforeEach(async () => {
    const module: TestingModule = await Test.createTestingModule({
      controllers: [ProductoControladorController],
    }).compile();

    controller = module.get<ProductoControladorController>(ProductoControladorController);
  });

  it('should be defined', () => {
    expect(controller).toBeDefined();
  });
});
