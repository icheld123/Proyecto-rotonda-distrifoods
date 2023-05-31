import { Component } from '@angular/core';
import { PedidosService } from './service/pedidos.service';
import { PedidoCompleto } from 'src/app/models/pedido';
import { Estado } from 'src/app/models/estado';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { HttpHeaders } from '@angular/common/http';

const ACTUALIZACION_CORRECTA = "El estado del pedido fue actualizado satisfactoriamente.";
const CABECERA = ["Id", "Cliente", "Método de pago", "Fecha(M/D/Y)","Hora", "Productos", "Total", "Datos cliente","Estado", "Acción"];

@Component({
  selector: 'app-pedidos',
  templateUrl: './pedidos.component.html',
  styleUrls: ['./pedidos.component.css']
})
export class PedidosComponent {

  public formEstado: FormGroup;
  public pedidos: PedidoCompleto[];
  public estados: Estado[];
  public pedidoSelected: PedidoCompleto;
  public cabecera = CABECERA;
  public mostrarModal: boolean = false;

  constructor(private pedidoService: PedidosService ){}

  async llamarServicioGetPedidos(){
    this.pedidoService.getPedidos().subscribe(respuesta => {
      this.pedidos = respuesta;
      // console.log(this.pedidos);
    })
  }

  async llamarServicioGetEstados(){
    this.pedidoService.getEstados().subscribe(respuesta => {
      this.estados = respuesta;
    })
  }

  public mostrarMensaje(mensaje: string){
    alert(mensaje);
  }

  async llamarServicioUpdateEstadoPedido(){
    if (this.formEstado.valid) {
      let valueSelectEstado = this.formEstado.value.estado;
      console.log(valueSelectEstado);
      let options = {
        headers: new HttpHeaders().set(
          'Content-Type',
          'application/json'
        )
      };
      try {
        this.pedidoService.actualizarPedido({id: this.pedidoSelected.id, idEstado: valueSelectEstado}, options).subscribe(
          (response: any) =>{
            console.log(response);
            if (response.id > 0 && response.idEstado == valueSelectEstado){ //NECESARIO REVISAR Y AJUSTAR VALIDACIÓN
              this.mostrarMensaje(ACTUALIZACION_CORRECTA);
              this.llamarServicioGetPedidos();
              this.cerrarModal();
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

  iniciarActualizacionEstado(pedido: PedidoCompleto){
    this.pedidoSelected = pedido;
    this.formEstado.controls['estado'].setValue(pedido.estado.id);
    this.abrirModal();
  }

  obtenerDatosFecha(fecha: Date){
    let fechaNew = new Date(fecha);
    return fechaNew.toLocaleDateString();
  }

  obtenerDatosHora(fecha: Date){
    let fechaNew = new Date(fecha);
    return fechaNew.toLocaleTimeString();
  }

  public abrirModal(){
    this.mostrarModal = true;
    //Se resetea el formulario para que se deseleccione las opciones que puedan existir
    // this.adicionForm.reset();
  }

  public cerrarModal(){
    this.mostrarModal = false;
  }

  public construirFormulario(){
    this.formEstado = new FormGroup({
      estado: new FormControl("",[Validators.required]),
    });
  }

  ngOnInit(): void {
    this.construirFormulario();
    this.llamarServicioGetPedidos();
    this.llamarServicioGetEstados();
  }
}
