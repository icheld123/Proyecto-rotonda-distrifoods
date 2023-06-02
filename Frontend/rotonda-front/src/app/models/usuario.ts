import { Cliente } from "./cliente";

export interface Usuario {
  id:  number;
  identificacion: number;
  nombre: string;
  contrasena: string;
  correo: string;
}


export interface UsuarioLogueadoResponse {
  cliente:  Cliente;
  identificacion: number;
  nombre: string;
  usuario: string;
  contrasena: string;
}

export interface UsuarioLogueadoRequest {
  identificacion: number;
  contrasena: string;
}

