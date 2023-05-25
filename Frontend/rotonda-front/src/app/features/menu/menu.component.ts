import { Component } from '@angular/core';
import { Producto } from '../../models/producto';
import { MenuServicioService } from './service/menu.servicio.service';
import { ActivatedRoute } from '@angular/router';
import { CartService } from '../shared/service/cart.service';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent {
  productos: Producto[] = [];
  idRestaurante: string = "";
  items: Producto[] = [];

  constructor(private menuService: MenuServicioService, private route: ActivatedRoute, public cartService: CartService){

  }

  async llamarServicioGetMenu(id: string){
    this.menuService.getProductos(id).subscribe(respuesta => {
      this.productos = respuesta;
      console.log(this.productos);
    })
  }

  public addToCart(item:Producto) {
    // if (!this.cartService.itemInCart(item)) {
      // item.qtyTotal = 1;
      this.cartService.addToCart(item); //add items in cart
      this.items = [...this.cartService.getItems()];
    // }
  }


  ngOnInit(): void {
    this.route.queryParams.subscribe(params =>{
      this.idRestaurante = params["id"];
      console.log(this.idRestaurante);
      this.llamarServicioGetMenu(this.idRestaurante);
    })
  }
}
