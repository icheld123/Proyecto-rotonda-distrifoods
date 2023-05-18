import { DataSource, DataSourceOptions } from "typeorm"
export const dataSourceOptions: DataSourceOptions ={
  type: 'mysql',
  host: 'localhost',
  port: 3306,
  username: 'root',
  password: '2210',
  database: 'proyectorotonda',
  entities: [__dirname + '/../**/*.entity.{ts,js}'],
  migrations: ['dist/database/migrations/*.js']
};

const dataSource = new DataSource(dataSourceOptions);

export default dataSource;
