import { Test, TestingModule } from '@nestjs/testing';
import { SucursalControladorController } from './sucursal.controlador.controller';

describe('SucursalControladorController', () => {
  let controller: SucursalControladorController;

  beforeEach(async () => {
    const module: TestingModule = await Test.createTestingModule({
      controllers: [SucursalControladorController],
    }).compile();

    controller = module.get<SucursalControladorController>(SucursalControladorController);
  });

  it('should be defined', () => {
    expect(controller).toBeDefined();
  });
});
