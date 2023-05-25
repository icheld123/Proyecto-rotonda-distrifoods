import { Component, OnInit } from '@angular/core';
import { Producto } from 'src/app/models/producto';
import { CartService } from '../shared/service/cart.service';

@Component({
  selector: 'app-carrito',
  templateUrl: './carrito.component.html',
  styleUrls: ['./carrito.component.css']
})
export class CarritoComponent implements OnInit{
  items:Producto[] = [];
  total:number = 0;

  constructor(public cartService: CartService){
  }

  removeFromCart(item: Producto) {
    this.cartService.removeItem(item);
    this.items = this.cartService.getItems();
    this.calcularTotal();
  }

  clearCart(items: Producto[]) {
    // this.items.forEach((item, index) => this.cartService.removeItem(index));
    this.cartService.clearCart(items);
    this.items = [...this.cartService.getItems()];
    this.calcularTotal();
  }

  calcularTotal(){
    this.total = 0;
    this.items.forEach(producto => {
      this.total = this.total + producto.precio;
    });
  }

  ngOnInit(): void {
    this.cartService.loadCart();
    this.items = this.cartService.getItems();
    this.calcularTotal();
  }

}
