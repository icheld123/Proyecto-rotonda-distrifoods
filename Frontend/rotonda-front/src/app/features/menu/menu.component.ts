import { Component } from '@angular/core';
import { Producto, ProductoAdicion } from '../../models/producto';
import { MenuServicioService } from './service/menu.servicio.service';
import { ActivatedRoute } from '@angular/router';
import { CartService } from '../shared/service/cart.service';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { EdicionAdicionServicioService } from '../edicion-adicion/service/edicion-adicion.servicio.service';
import { Adicion } from 'src/app/models/adicion';
import { Restaurante } from 'src/app/models/restaurante';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent {
  productos: Producto[] = [];
  restauranteNombre: string = "";
  idRestaurante: string = "";
  items: ProductoAdicion[] = [];
  adicion: FormGroup;
  adiciones: Adicion[];
  adicionesOrganizadas: any[] = [[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[]]; //revisar para que no salga en consola invalido si no genero arreglos vacios

  constructor(private menuService: MenuServicioService,
              private route: ActivatedRoute,
              public cartService: CartService,
              private edicionAdicionService: EdicionAdicionServicioService){

  }

  async llamarServicioGetMenu(id: string){
    this.menuService.getProductos(id).subscribe(respuesta => {
      this.productos = respuesta;
      this.llamarServicioGetAdiciones();
    })
  }

  async llamarServicioGetRestauranteById(id: string){
    this.menuService.getRestauranteById(id).subscribe(respuesta => {
      this.restauranteNombre = respuesta.nombre;
      // console.log(this.productos);
    })
  }

  public addToCart(producto:Producto, id:number) {
    console.log(this.adicion.value.adiciones);
    // let productoConAdicion = {
    //   id: producto.id,
    //   nombreRestaurante: producto.nombreRestaurante,
    //   nombreProducto: producto.nombreProducto,
    //   tipoProducto: producto.tipoProducto,
    //   cantidad: producto.cantidad,
    //   adiciones: this.adicion.value.adiciones,
    //   descripcion: producto.descripcion,
    //   imagen: producto.imagen,
    //   precio: producto.precio
    // }

    // this.cartService.addToCart(productoConAdicion);
    // this.items = [...this.cartService.getItems()];
    // alert("El producto fue agregado al carrito (id:"+ productoConAdicion.id +")")
  }

  async llamarServicioGetAdiciones(){
    this.edicionAdicionService.getAdiciones().subscribe(respuesta => {
      this.adiciones = respuesta;
      this.organizarAdicionesPorProducto();
    })
  }

  organizarAdicionesPorProducto(){
    this.adicionesOrganizadas = [];
    for (let index = 0; index < this.productos.length; index++) {
      const producto = this.productos[index];
      this.adicionesOrganizadas.push([]);
      this.adiciones.forEach(adicion => {
        if(producto.id == adicion.idProducto){
          this.adicionesOrganizadas[index].push(adicion);
        }
      });
    }
  }

  public onSubmit(){
    console.log(this.adicion.value);
  }

  public construirFormulario(){
    this.adicion = new FormGroup({
      adiciones: new FormControl()
    });
  }

  ngOnInit(): void {
    this.route.queryParams.subscribe(params =>{
      this.idRestaurante = params["id"];
      console.log(this.idRestaurante);
      this.llamarServicioGetMenu(this.idRestaurante);
      this.llamarServicioGetRestauranteById(this.idRestaurante);
      // this.llamarServicioGetAdiciones();
      this.construirFormulario();
    });
  }
}
