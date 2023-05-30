import { Component } from '@angular/core';
import { PedidosService } from './service/pedidos.service';
import { PedidoCompleto } from 'src/app/models/pedido';

const CABECERA = ["Id", "Cliente", "Método de pago", "Fecha(MM/DD/YYYY)", "Total", "Productos", "Estado", "Acción"];

@Component({
  selector: 'app-pedidos',
  templateUrl: './pedidos.component.html',
  styleUrls: ['./pedidos.component.css']
})
export class PedidosComponent {

  public pedidos: PedidoCompleto[];
  public cabecera = CABECERA;

  constructor(private pedidoService: PedidosService ){}

  async llamarServicioGetPedidos(){
    this.pedidoService.getPedidos().subscribe(respuesta => {
      this.pedidos = respuesta;
      console.log(this.pedidos);
    })
  }

  obtenerDatosFechaHora(fecha: Date){
    let fechaNew = new Date(fecha);
    // console.log(fechaNew);
    // console.log(fechaNew.toLocaleDateString() + " - " + fechaNew.toLocaleTimeString());
    return fechaNew.toLocaleDateString() + " - " + fechaNew.toLocaleTimeString();
  }

  ngOnInit(): void {
    this.llamarServicioGetPedidos();
  }
}
