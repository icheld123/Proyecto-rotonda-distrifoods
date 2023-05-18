import { Test, TestingModule } from '@nestjs/testing';
import { EstadoControladorController } from './estado.controlador.controller';

describe('EstadoControladorController', () => {
  let controller: EstadoControladorController;

  beforeEach(async () => {
    const module: TestingModule = await Test.createTestingModule({
      controllers: [EstadoControladorController],
    }).compile();

    controller = module.get<EstadoControladorController>(EstadoControladorController);
  });

  it('should be defined', () => {
    expect(controller).toBeDefined();
  });
});
