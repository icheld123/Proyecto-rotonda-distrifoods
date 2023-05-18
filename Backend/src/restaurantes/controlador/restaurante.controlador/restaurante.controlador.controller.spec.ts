import { Test, TestingModule } from '@nestjs/testing';
import { RestauranteControladorController } from './restaurante.controlador.controller';

describe('RestauranteControladorController', () => {
  let controller: RestauranteControladorController;

  beforeEach(async () => {
    const module: TestingModule = await Test.createTestingModule({
      controllers: [RestauranteControladorController],
    }).compile();

    controller = module.get<RestauranteControladorController>(RestauranteControladorController);
  });

  it('should be defined', () => {
    expect(controller).toBeDefined();
  });
});
