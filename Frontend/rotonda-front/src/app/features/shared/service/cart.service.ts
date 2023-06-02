import { Injectable } from "@angular/core";
import { ProductoCompleto } from "src/app/models/producto";

@Injectable({
  providedIn: "root"
})
export class CartService {
  constructor() {}

  items:ProductoCompleto[] = [];

  addToCart(addedItem: ProductoCompleto) {
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

  clearCart(items: ProductoCompleto[]) {
    this.items = [];

    localStorage.removeItem("cart_items")
  }

  removeItem(item: ProductoCompleto) {
    const index = this.items.findIndex(o => o.id === item.id);

    if (index > -1) {
      this.items.splice(index, 1);
      this.saveCart();
    }
  }

  removeItemById(id: number) {
    const index = this.items.findIndex(o => o.id === id);

    if (index > -1) {
      this.items.splice(index, 1);
      this.saveCart();
    }
  }

  itemInCart(item: ProductoCompleto): boolean {
    return this.items.findIndex(o => o.id === item.id) > -1;
  }

  itemInCartById(id: number): boolean {
    return this.items.findIndex(o => o.id === id) > -1;
  }
}
