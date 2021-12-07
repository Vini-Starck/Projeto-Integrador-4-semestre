import { ItemVenda } from "./ItemVenda";
import { Usuario } from "./usuario";

export class Venda {
    id_venda?: number;
    num_venda?: number;
    data_venda?: Date;
    usuario?: Usuario;
    itensVendidos?: Array<ItemVenda>;

}