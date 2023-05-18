import { Test, TestingModule } from '@nestjs/testing';
import { AdicionController } from './adicion.controlador.controller';

describe('AdicionController', () => {
  let controller: AdicionController;

  beforeEach(async () => {
    const module: TestingModule = await Test.createTestingModule({
      controllers: [AdicionController],
    }).compile();

    controller = module.get<AdicionController>(AdicionController);
  });

  it('should be defined', () => {
    expect(controller).toBeDefined();
  });
});
