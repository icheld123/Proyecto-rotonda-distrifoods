import { HttpHeaders } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { credencialesAnonimo } from 'src/environments/environment';
import { RegistroService } from './registro.service';

const CREACION_CORRECTA = "El usuario ha sido creado exitosamente.";

@Component({
  selector: 'app-registro',
  templateUrl: './registro.component.html',
  styleUrls: ['./registro.component.css']
})
export class RegistroComponent {
  public formRegistro: FormGroup;

  constructor(public registroService: RegistroService){
  }

  public llamarServicioPostRegistro(){
    if (this.formRegistro.valid){
      let body = {
        id: 0,
        usuario:{
          id: 0,
          identificacion: this.formRegistro.value.identificacion,
          nombre: this.formRegistro.value.nombre,
          contrasena: this.formRegistro.value.contrasena,
          correo: this.formRegistro.value.correo,
        },
        direccion: this.formRegistro.value.direccion,
        telefono: this.formRegistro.value.telefono,
      }
      let options = {
        headers: new HttpHeaders({
          'Content-Type':'application/json',
          'Authorization': 'Basic ' + btoa(credencialesAnonimo.usuario + ":" + credencialesAnonimo.contrasena)
        })
      };
      try {
        this.registroService.crearUsuario(body, options).subscribe(
            (response: any) =>{
              if(response.id > 0){
                this.mostrarMensaje(CREACION_CORRECTA);
                this.setearFormProducto();
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

  public mostrarMensaje(mensaje: string){
    alert(mensaje);
  }


  public setearFormProducto(){
    this.formRegistro.controls['identificacion'].setValue("");
    this.formRegistro.controls['nombre'].setValue("");
    this.formRegistro.controls['correo'].setValue("");
    this.formRegistro.controls['contrasena'].setValue("");
    this.formRegistro.controls['direccion'].setValue("");
    this.formRegistro.controls['telefono'].setValue("");
  }

  public construirFormulario(){
    this.formRegistro = new FormGroup({
      identificacion: new FormControl("", [Validators.required]),
      nombre: new FormControl("", [Validators.required]),
      correo: new FormControl("", [Validators.required, Validators.email]),
      contrasena: new FormControl("", [Validators.required]),
      direccion: new FormControl("", [Validators.required]),
      telefono: new FormControl("", [Validators.required])
    });
  }

  ngOnInit(): void {
    this.construirFormulario();

  }
}
