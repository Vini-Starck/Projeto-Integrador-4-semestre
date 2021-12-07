import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UsuarioComponent } from './views/usuario/usuario.component';
import { SobreComponent } from './views/sobre/sobre.component';
import { ProdutoComponent } from './views/produto/produto.component';
import { CategoriaComponent } from './views/categoria/categoria.component';
import { VendaComponent } from './views/venda/venda.component';

const routes: Routes = [
  {
    path: 'sobre',
    component: SobreComponent
  },
  {
    path: 'usuario',
    component: UsuarioComponent
  },
  {
    path: 'produto',
    component: ProdutoComponent
  },
  {
    path: 'venda',
    component: VendaComponent
  },
  {
    path: 'categoria',
    component: CategoriaComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
