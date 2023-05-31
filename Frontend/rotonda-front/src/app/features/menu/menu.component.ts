import { Component } from '@angular/core';
import { Producto, ProductoCompleto } from '../../models/producto';
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
  productos: ProductoCompleto[] = [];
  restauranteNombre: string = "";
  idRestaurante: string = "";
  items: ProductoCompleto[] = [];
  adicionForm: FormGroup;
  productoSelected: ProductoCompleto;
  adiciones: Adicion[];
  public mostrarModal: boolean = false;
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

  public initAddToCart(producto:ProductoCompleto){
    console.log("***********Adiciones seleccionadas************");
    console.log(this.adicionForm.value.adiciones);
    console.log("**********************************************");
    this.productoSelected = producto;
    this.abrirModal();
  }

  public addToCart() {
    console.log(this.adicionForm.value.adiciones);
    let productoConAdicionSeleccionada = {
      id: this.productoSelected.id,
      restaurante: this.productoSelected.restaurante,
      nombre: this.productoSelected.nombre,
      tipoProducto: this.productoSelected.tipoProducto,
      cantidad: this.productoSelected.cantidad,
      descripcion: this.productoSelected.descripcion,
      imagen: this.productoSelected.imagen,
      precio: this.productoSelected.precio,
      adiciones: this.adicionForm.value.adiciones
    }

    this.cartService.addToCart(productoConAdicionSeleccionada);
    this.items = [...this.cartService.getItems()];
    this.cerrarModal();
    alert("El producto fue agregado al carrito (id:"+ productoConAdicionSeleccionada.id +")")
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
    console.log(this.adicionForm.value);
  }

  public construirFormulario(){
    this.adicionForm = new FormGroup({
      adiciones: new FormControl("")
    });
  }

  public abrirModal(){
    this.mostrarModal = true;
    //Se resetea el formulario para que se deseleccione las opciones que puedan existir
    this.adicionForm.reset();
  }

  public cerrarModal(){
    this.mostrarModal = false;
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
