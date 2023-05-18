import { Component } from '@angular/core';
import { Adicion } from 'src/app/models/adicion';
import {EdicionAdicionServicioService} from './edicion-adicion.servicio.service';
import { Producto } from 'src/app/models/producto';

@Component({
  selector: 'app-edicion-adicion',
  templateUrl: './edicion-adicion.component.html',
  styleUrls: ['./edicion-adicion.component.css']
})
export class EdicionAdicionComponent {
  adiciones: Adicion[] = [];
  productos: Producto[] = [];
  adicionNueva: Adicion = {
  id_producto: 0,
  nom_adicion: '',
  precio: 0
  }

  constructor(private edicionAdicionService: EdicionAdicionServicioService){

  }
  async llamarServicioGetProducto(){
    this.edicionAdicionService.getProductos().subscribe(respuesta => {
      this.productos = respuesta;
    })
  }

  submitAdicion(){
    this.edicionAdicionService.crearAdicion(this.adicionNueva)
    .subscribe( res=>{
      console.log(res);
      //this.router.navigate(['/']);
    },
    err=>console.log(err)
    )
  }
  async llamarServicioGetAdiciones(){
    this.edicionAdicionService.getAdiciones().subscribe(respuesta => {
      this.adiciones = respuesta;
    })
  }
  private mapearAdiciones(adiciones: Adicion[]){
    return adiciones;
  }
  private mapearProductos(productos: Producto[]){
    return productos;
  }

  ngOnInit(): void {
    this.llamarServicioGetAdiciones();
    this.llamarServicioGetProducto();
  }
}
