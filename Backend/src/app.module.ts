import { Module } from '@nestjs/common';
import { TypeOrmModule } from '@nestjs/typeorm';
import { AppController } from './app.controller';
import { AppService } from './app.service';
import { RestaurantesModule } from './restaurantes/restaurantes.module';
import { typeOrmConfig } from './config/typeorm.config';
//import { dataSourceOptions } from 'database/data_source';

@Module({
  imports: [
    TypeOrmModule.forRoot(typeOrmConfig),
    RestaurantesModule,
  ],
  controllers: [
        AppController],
  providers: [AppService],
})
export class AppModule {}
