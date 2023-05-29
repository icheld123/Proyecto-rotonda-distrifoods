import { Injectable } from "@angular/core";
import { Producto, ProductoAdicion } from "src/app/models/producto";

@Injectable({
  providedIn: "root"
})
export class CartService {
  constructor() {}

  items:ProductoAdicion[] = [];

  addToCart(addedItem: ProductoAdicion) {
    this.items.push(addedItem);
    // console.log(addedItem);

    //-----check if there are items already added in cart
    /* let existingItems = [];
    if ( localStorage.getItem('cart_items')){//----- update by adding new items
      existingItems = JSON.parse(localStorage.getItem('cart_items'));
      existingItems = [addedItem, ...existingItems];
      console.log( 'Items exists');
    } */
    //-----if no items, add new items
    /* else{
      console.log( 'NO items exists');
      existingItems = [addedItem]
    } */

    this.saveCart();
  }

  getItems() {
    return this.items;
  }

  loadCart(): void {
    let contenidoLocalStorage = localStorage.getItem("cart_items");
    if (contenidoLocalStorage){
      this.items = JSON.parse(contenidoLocalStorage) ?? [];
    }
  }

  saveCart(): void {
    localStorage.setItem('cart_items', JSON.stringify(this.items));
    console.log(localStorage.getItem("cart_items"));
  }

  clearCart(items: ProductoAdicion[]) {
    this.items = [];

    localStorage.removeItem("cart_items")
  }

  removeItem(item: ProductoAdicion) {
    const index = this.items.findIndex(o => o.id === item.id);

    if (index > -1) {
      this.items.splice(index, 1);
      this.saveCart();
    }
  }

  itemInCart(item: ProductoAdicion): boolean {
    return this.items.findIndex(o => o.id === item.id) > -1;
  }
}
