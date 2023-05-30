import { Usuario } from "./usuario";

export interface Cliente {
  id:  number;
  idUsuario: number;
  direccion: string;
  telefono: number;
}


export interface ClienteCompleto {
  id:  number;
  usuario: Usuario;
  direccion: string;
  telefono: number;
}
