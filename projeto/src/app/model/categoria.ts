export class Categoria {

    id_cat?: number;
    nome?: string;

    public getId_cat() {
        return this.id_cat;
    }

    public setId_cat(id_cat: undefined): void {
        this.id_cat = id_cat;
    }

    public getNome() {
        return this.nome;
    }

    public setNome(nome: undefined): void {
        this.nome = nome;
    }

 
}