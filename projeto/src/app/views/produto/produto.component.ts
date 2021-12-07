import { Component, OnInit } from '@angular/core';
import { ProdutoService } from 'src/app/services/produto.service';
import { Produto } from 'src/app/model/produto';
import { Categoria } from 'src/app/model/categoria';
import { CategoriaComponent } from '../categoria/categoria.component';
import { CategoriaService } from 'src/app/services/categoria.service';

@Component({
  selector: 'app-produto',
  templateUrl: './produto.component.html',
  styleUrls: ['./produto.component.css']
})
export class ProdutoComponent implements OnInit {
  busca: string = "";
  produtos = new Array<Produto>();
  produtoEdicao?: Produto;
  estaEditando = false;
  pesquisa = new Array<Produto>();
  categorias = new Array<Categoria>();

  constructor(private produtoService: ProdutoService, private categoriaService: CategoriaService) { }

  ngOnInit(): void {
    this.listar();
    if(this.busca != undefined){
      this.pesquisar();
    }
  }

  pesquisar(): void{

   
      this.produtoService.pesquisar(this.busca).subscribe(pesquisa => {
      this.pesquisa = pesquisa;
    });
    
  }

  listar(): void {
    this.produtoService.listar().subscribe(produtos => {
      this.produtos = produtos;
    });

    this.categoriaService.listar().subscribe(categorias => {
      this.categorias = categorias;
    })

  }

  salvar(): void {
    if(this.produtoEdicao == undefined){
      return;
    }

    if(!this.estaEditando){
      this.produtoService.inserir(this.produtoEdicao).subscribe(() => {
        this.listar();
        this.cancelar();
      });
    }
    else{
      this.produtoService.atualizar(this.produtoEdicao).subscribe(() => {
        this.listar();
        this.cancelar();
      });
    }
    
  }

  novoProduto(){
    this.produtoEdicao = new Produto();
    this.estaEditando = false;
  }

  cancelar() {
    this.produtoEdicao = undefined;
    this.estaEditando = false;
  }

  selecionarProduto(produto: Produto){
    this.produtoEdicao = produto;
    this.estaEditando = true;
  }

  excluir(produto: Produto){

    const resposta = confirm(`${produto?.nome} será exclído`);

    if(resposta && produto && produto.id_prod){
      this.produtoService.excluir(produto.id_prod).subscribe(() => {
        this.listar();
      });
    }
  }

  

}