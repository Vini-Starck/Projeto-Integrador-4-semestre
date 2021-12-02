package br.senac.exemplo_cadastro;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Venda {
	private int id_venda;
	private int num_venda;
	private LocalDate data_venda;
	private Usuario usuario;
	private List<ItemVenda> itensVendidos = new ArrayList<ItemVenda>();
	
	
	public int getId_venda() {
		return id_venda;
	}
	public void setId_venda(int id_venda) {
		this.id_venda = id_venda;
	}
	public int getNum_venda() {
		return num_venda;
	}
	public void setNum_venda(int num_venda) {
		this.num_venda = num_venda;
	}
	public LocalDate getData_venda() {
		return data_venda;
	}
	public void setData_venda(LocalDate data_venda) {
		this.data_venda = data_venda;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public List<ItemVenda> getItensVendidos() {
		return itensVendidos;
	}
	public void setItensVendidos(List<ItemVenda> itensVendidos) {
		this.itensVendidos = itensVendidos;
	}
	
	
	
}
