import { Categoria } from "./categoria";

export class Produto {

    id_prod?: number;
    nome?: string;
    codigo?: number;
	preco?: number;
	fabricante?: string;
    descricao?: string;
    estoque?: number;
    categoria: Categoria = new Categoria();

    public getCategoria() {
        return this.categoria;
    }

    public setCategoria(a :Categoria) {
        this.categoria = a;
    }

    public getId_prod() {
        return this.id_prod;
    }

    public setId_prod(id_prod: number): void {
        this.id_prod = id_prod;
    }

    public getNome() {
        return this.nome;
    }

    public setNome(nome: string): void {
        this.nome = nome;
    }

    public getCodigo() {
        return this.codigo;
    }

    public setCodigo(codigo: number): void {
        this.codigo = codigo;
    }

    public getPreco() {
        return this.preco;
    }

    public setPreco(preco: number): void {
        this.preco = preco;
    }

    public getFabricante() {
        return this.fabricante;
    }

    public setFabricante(descricao: string): void {
        this.fabricante = descricao;
    }

    public getDescricao() {
        return this.descricao;
    }

    public setDescricao(descricao: string): void {
        this.descricao = descricao;
    }

    public getEstoque() {
        return this.estoque;
    }

    public setEstoque(estoque: number): void {
        this.estoque = estoque;
    }





}