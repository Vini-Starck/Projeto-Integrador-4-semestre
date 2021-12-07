import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { SobreComponent } from './views/sobre/sobre.component';
import { UsuarioComponent } from './views/usuario/usuario.component';
import { FormsModule } from '@angular/forms';
import { ProdutoComponent } from './views/produto/produto.component';
import { CategoriaComponent } from './views/categoria/categoria.component';
import { VendaComponent } from './views/venda/venda.component';


@NgModule({
  declarations: [
    AppComponent,
    SobreComponent,
    UsuarioComponent,
    ProdutoComponent,
    CategoriaComponent,
    VendaComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
