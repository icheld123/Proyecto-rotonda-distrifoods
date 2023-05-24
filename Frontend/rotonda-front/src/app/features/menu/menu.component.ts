import { Component } from '@angular/core';
import { Producto } from '../../models/producto';
import { MenuServicioService } from './menu.servicio.service';
import { Sucursal } from 'src/app/models/sucursal';
import { Restaurante } from 'src/app/models/restaurante';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent {
  productos: Producto[] = [];
  idRestaurante: string = "";

  constructor(private menuService: MenuServicioService, private route: ActivatedRoute){

  }

  async llamarServicioGetMenu(id: string){
    this.menuService.getProductos(id).subscribe(respuesta => {
      this.productos = respuesta;
      console.log(this.productos);
    })
  }


  ngOnInit(): void {


    this.route.queryParams.subscribe(params =>{
      this.idRestaurante = params["id"];
      console.log(this.idRestaurante);
      this.llamarServicioGetMenu(this.idRestaurante);
    })
  }
}
