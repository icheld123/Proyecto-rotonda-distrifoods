import { Test, TestingModule } from '@nestjs/testing';
import { MetodosPagoControladorController } from './metodos_pago.controlador.controller';

describe('MetodosPagoControladorController', () => {
  let controller: MetodosPagoControladorController;

  beforeEach(async () => {
    const module: TestingModule = await Test.createTestingModule({
      controllers: [MetodosPagoControladorController],
    }).compile();

    controller = module.get<MetodosPagoControladorController>(MetodosPagoControladorController);
  });

  it('should be defined', () => {
    expect(controller).toBeDefined();
  });
});
