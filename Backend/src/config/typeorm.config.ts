import { TypeOrmModuleOptions } from "@nestjs/typeorm";

export const typeOrmConfig: TypeOrmModuleOptions = {
  type: 'mysql',
  host: 'localhost',
  port: 3306,
  username: 'root',
  password: '2210',
  database: 'proyectorotonda',
  entities: [__dirname + '/../**/*.entity.{ts,js}'],
  migrations: ["migration/*.js"],
  synchronize: true,
  migrationsRun: true,
}
