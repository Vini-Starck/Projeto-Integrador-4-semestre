import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Venda } from '../model/venda';
import { HttpClient, HttpParams } from '@angular/common/http';
import {formatDate} from '@angular/common';

@Injectable({
  providedIn: 'root'
})
export class VendaService {

  constructor(private http: HttpClient) { }

  fazerVenda(venda: Venda): Observable<void> {
    return this.http.post<void>('http://localhost:8080/exemplo-cadastro/webapi/venda', venda);
  }

  relatorio(dataI: Date, dataF: Date): Observable<Venda[]> {
    let param1 = new HttpParams();
    param1 = param1.append('dataI', formatDate(dataI,'yyyy-MM-dd','en-US'));
    let param2 = new HttpParams();
    param2 = param2.append('dataF', formatDate(dataF,'yyyy-MM-dd','en-US'));
    return this.http.get<Venda[]>('http://localhost:8080/exemplo-cadastro/webapi/venda', {params: param1 && param2});
  }

}
