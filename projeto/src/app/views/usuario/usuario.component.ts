import { Component, OnInit } from '@angular/core';
import { Usuario } from 'src/app/model/usuario';
import { UsuarioService } from 'src/app/services/usuario.service';

@Component({
  selector: 'app-usuario',
  templateUrl: './usuario.component.html',
  styleUrls: ['./usuario.component.css']
})
export class UsuarioComponent implements OnInit {

  busca: string = "";
  usuarios = new Array<Usuario>();
  usuarioEdicao?: Usuario = undefined;
  estaEditando = false;
  pesquisa = new Array<Usuario>();
  usuarioPesquisa?: Usuario = undefined;

  constructor(private usuarioService: UsuarioService) { }

  ngOnInit(): void {
  
    if(this.busca != undefined){
      this.pesquisar();
    }
  }

  pesquisar(): void{

   
      this.usuarioService.pesquisar(this.busca).subscribe(pesquisa => {
      this.pesquisa = pesquisa;
    });
    
  }

  listar(): void {
    this.usuarioService.listar().subscribe(usuarios => {
      this.usuarios = usuarios;
    });
  }

  salvar(): void {
    if(this.usuarioEdicao == undefined){
      return;
    }

    if(!this.estaEditando){
      this.usuarioService.inserir(this.usuarioEdicao).subscribe(() => {
        this.listar();
        this.cancelar();
      });
    }
    else{
      this.usuarioService.atualizar(this.usuarioEdicao).subscribe(() => {
        this.listar();
        this.cancelar();
      });
    }
    
  }

  novoUsuario(){
    this.usuarioEdicao = new Usuario();
    this.estaEditando = false;
  }

  cancelar() {
    this.usuarioEdicao = undefined;
    this.estaEditando = false;
  }

  selecionarUsuario(usuario: Usuario){
    this.usuarioEdicao = usuario;
    this.estaEditando = true;
  }

  excluir(usuario: Usuario){

    const resposta = confirm(`${usuario?.nome} será exclído`);

    if(resposta && usuario && usuario.id){
      this.usuarioService.excluir(usuario.id).subscribe(() => {
        this.listar();
      });
    }
  }

  

}
