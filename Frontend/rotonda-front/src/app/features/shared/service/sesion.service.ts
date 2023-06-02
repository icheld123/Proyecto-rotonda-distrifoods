import { Injectable } from "@angular/core";
import { UsuarioLogueadoResponse } from "src/app/models/usuario";

@Injectable({
  providedIn: "root"
})
export class SesionService {
  constructor() {}

  data: UsuarioLogueadoResponse;

  addData(sesionData: UsuarioLogueadoResponse) {
    this.data = sesionData;
    this.saveSesionData();
  }

  getSesionData() {
    this.loadSesionData();
    return this.data;
  }

  loadSesionData(): void {
    let contenidoLocalStorage = localStorage.getItem("login");
    if (contenidoLocalStorage){
      this.data = JSON.parse(contenidoLocalStorage);
    }
  }

  saveSesionData(): void {
    localStorage.setItem('login', JSON.stringify(this.data));
    console.log(localStorage.getItem("login"));
  }

  clearSesionData() {
    this.data.contrasena = "";
    this.data.identificacion = 0;
    this.data.nombre = ""
    this.data.usuario = ""
    if (this.data.cliente != null) {
      this.data.cliente.direccion = "";
      this.data.cliente.id = 0;
      this.data.cliente.idUsuario = 0;
      this.data.cliente.telefono = 0
    }
    localStorage.removeItem("login")
  }

}
