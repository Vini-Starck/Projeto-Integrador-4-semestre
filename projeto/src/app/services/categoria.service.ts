import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Categoria } from '../model/categoria';

@Injectable({
  providedIn: 'root'
})
export class CategoriaService {

  constructor(private http: HttpClient) { }

  listar(): Observable<Categoria[]> {
    return this.http.get<Categoria[]>('http://localhost:8080/exemplo-cadastro/webapi/categoria');
  }

  inserir(categoria: Categoria): Observable<void> {
    return this.http.post<void>('http://localhost:8080/exemplo-cadastro/webapi/categoria', categoria);
  }

  atualizar(categoria: Categoria): Observable<void> {
    return this.http.put<void>('http://localhost:8080/exemplo-cadastro/webapi/categoria', categoria);
  }

  excluir(id: number): Observable<void> {
    let parametro = new HttpParams();
    parametro = parametro.append('id', id);
    return this.http.delete<void>('http://localhost:8080/exemplo-cadastro/webapi/categoria', {params: parametro});
  }
}
