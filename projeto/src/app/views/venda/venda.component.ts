import { Component, OnInit } from '@angular/core';
import { Categoria } from 'src/app/model/categoria';
import { Produto } from 'src/app/model/produto';
import { Venda } from 'src/app/model/venda';
import { ItemVenda } from 'src/app/model/ItemVenda';
import { ProdutoService } from 'src/app/services/produto.service';
import { UsuarioService } from 'src/app/services/usuario.service';
import { VendaService } from 'src/app/services/venda.service';
import { Usuario } from 'src/app/model/usuario';


@Component({
  selector: 'app-venda',
  templateUrl: './venda.component.html',
  styleUrls: ['./venda.component.css']
})
export class VendaComponent implements OnInit {
  
  vendaEdicao?: Venda;
  vendas = new Array<Venda>();
  usuarios = new Array<Usuario>();
  produtos = new Array<Produto>();
  estaEditando = false;

  constructor(private vendaService: VendaService, private produtoService: ProdutoService, private usuarioService: UsuarioService) { }

  ngOnInit(): void {
  }

  fazerVenda(venda: Venda){
    this.vendaService.fazerVenda(venda).subscribe(() => {});

  }

  cancelar() {
    this.vendaEdicao = undefined;
  }

  novaVenda() {

  }

}
