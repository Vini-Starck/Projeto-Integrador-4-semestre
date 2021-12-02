package br.senac.exemplo_cadastro;

public class ItemVenda {
	private Produto produto;
	private int qtd_vend;
	private float preco_vend;
	
	
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
	public int getQtd_vend() {
		return qtd_vend;
	}
	public void setQtd_vend(int qtd_vend) {
		this.qtd_vend = qtd_vend;
	}
	
	public float getPreco_vend() {
		return preco_vend;
	}
	public void setPreco_vend(float preco_vend) {
		this.preco_vend = preco_vend;
	}
	
	
}
