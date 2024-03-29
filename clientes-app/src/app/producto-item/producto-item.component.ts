import { ActivatedRoute, Router } from '@angular/router';
import { ProductoService } from './../producto/producto.service';
import { Component, OnInit, OnChanges, SimpleChanges } from '@angular/core';
import { Producto } from '../producto/producto.model';

@Component({
  selector: 'app-producto-item',
  templateUrl: './producto-item.component.html',
  styleUrls: ['./producto-item.component.css']
})
export class ProductoItemComponent implements OnInit {

  private imagenSeleccionada: File;
  private descripcionImagen: string;

  producto: Producto;

  constructor(private productoService: ProductoService,
    private activatedRoute: ActivatedRoute, private router: Router) { }



  ngOnInit() {
    this.activatedRoute.paramMap.subscribe(params =>
      {
        let id:number = +params.get('id')!;
        if (id){
          this.productoService.getProductoById(id).subscribe((producto: any) =>{
            this.producto = producto;
            console.log(this.producto?.productoCaracteristicas.descripcion);
          });
        }
      });
  }

  selecionarFoto(event: Event){
    this.imagenSeleccionada = (<HTMLInputElement>event.target).files![0];
  }

  asignarDescripcion(event: Event){
    this.descripcionImagen = (<HTMLInputElement>event.target).value;
  }


  subirImagen(){
    this.productoService.subirFoto(this.imagenSeleccionada, String(this.producto.id), this.descripcionImagen)
    .subscribe((producto: Producto) => {
      this.producto = producto;
    });
  }

  botonTerminar(): void {
    this.router.navigate(["/detalleproducto/"+ this.producto.id]);
  }


  eliminaImagen(imagen: string){
    this.productoService.eliminarImagen(imagen).subscribe(() => { });
    this.producto.productoCaracteristicas.imagenesProducto = this.producto.productoCaracteristicas.imagenesProducto.filter(x => x.imagen != imagen);


  }



}
