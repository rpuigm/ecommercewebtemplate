import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable, OnInit } from "@angular/core";
import { Observable } from "rxjs";
import { Usuario } from "../login/usuario.model";
import { AltaPersona } from './altapersona.model';

@Injectable({
  providedIn: "root"
})
export class AltaPersonaService implements OnInit{

  private altaPersona: AltaPersona;
  private httpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });
  private urlAltaCliente="http://https://psychic-succotash-5x944xv45qjh4wxr-8090.app.github.dev/api/personas/crea-cliente"

  constructor(private httpClient: HttpClient){}
  ngOnInit(): void {
  }

  altaCliente(usuario: Usuario): Observable<Usuario>{

    this.altaPersona= new AltaPersona();
    this.altaPersona.nombre=usuario.nombre
    this.altaPersona.apellidos=usuario.apellidos
    this.altaPersona.edad=usuario.edad
    this.altaPersona.username=usuario.username
    this.altaPersona.password=usuario.password

    return this.httpClient.post<Usuario>(this.urlAltaCliente, this.altaPersona, {
      headers: this.httpHeaders
    });
  }

}
