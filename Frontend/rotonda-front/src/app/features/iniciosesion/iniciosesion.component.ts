import { Component } from '@angular/core';
import { SesionService } from '../shared/service/sesion.service';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { InicioSesionService } from './iniciosesion.service';
import { HttpHeaders } from '@angular/common/http';
import { UsuarioLogueadoResponse } from 'src/app/models/usuario';
import { Router } from '@angular/router';

const USUARIO_INCORRECTO = "Revise nuevamente las credenciales ingresadas, el usuario o contraseña son incorrectos.";
const SESION_CERRADA = "Tu sesión se ha cerrado satisfactoriamente.";

@Component({
  selector: 'app-iniciosesion',
  templateUrl: './iniciosesion.component.html',
  styleUrls: ['./iniciosesion.component.css']
})
export class IniciosesionComponent {

  public formLogin: FormGroup;
  public existeUnaSesion: boolean;
  public sesionData: UsuarioLogueadoResponse;

  constructor(public sesionService: SesionService,
              private inicioSesionService: InicioSesionService,
              private router: Router){}

  public construirFormulario(){
    this.formLogin = new FormGroup({
      identificacion: new FormControl("", [Validators.required]),
      contrasena: new FormControl("", [Validators.required])
    });
  }

  public llamarServicioPostLogin(){
    let datosLogin = this.formLogin.value;
    if (this.formLogin.valid) {
      let options = {
        headers: new HttpHeaders().set(
          'Content-Type',
          'application/json'
        )
      };
      try {
        this.inicioSesionService.login(datosLogin, options).subscribe(
          (response: any) =>{
            console.log(response);
            if (response.identificacion != null && response.identificacion > 0){
              this.sesionService.addData(response);
              this.validarExistenciaSesion();
              this.router.navigateByUrl("login");
            }
            else {
              this.mostrarMensaje(USUARIO_INCORRECTO);
            }
          }),
          (error: any) => {
            console.log(error);
          }
      } catch (error) {
        console.log(error);
      }
    }
  }

  public cerrarSesion(){
    this.sesionService.clearSesionData();
    this.validarExistenciaSesion();
    this.mostrarMensaje(SESION_CERRADA);
    this.router.navigateByUrl("login");
    this.resetFormLogin();
  }

  public resetFormLogin(){
    this.formLogin.controls['identificacion'].setValue("");
    this.formLogin.controls['contrasena'].setValue("");
  }

  public validarExistenciaSesion(){
    this.sesionData = this.sesionService.getSesionData();
    this.existeUnaSesion = (this.sesionData != null && this.sesionData.identificacion > 0) ? true : false;
    console.log(this.existeUnaSesion);
  }

  public mostrarMensaje(mensaje: string){
    alert(mensaje);
  }

  ngOnInit(): void {
    this.construirFormulario();
    this.validarExistenciaSesion();
  }

}
