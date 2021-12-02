package br.senac.exemplo_cadastro;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DAOVenda {
	public static void fazerVenda(Venda venda) throws Exception{
		String sql = "insert into venda (num_venda, data_venda, id_usuario) "
				+ "VALUES (?, ?, ?)";
		
		
		try (PreparedStatement ps = DB.connect().prepareStatement(sql,
				PreparedStatement.RETURN_GENERATED_KEYS)) {
			ps.setInt(1, venda.getNum_venda());
			ps.setDate(2, Date.valueOf(venda.getData_venda()));
			ps.setInt(3, venda.getUsuario().getId());
			ps.execute();
			
			ResultSet rsKeys = ps.getGeneratedKeys();
			if(rsKeys.next()) {
				int idGerado = rsKeys.getInt(1);
				
				inserirProdutosVendidos(idGerado, venda.getItensVendidos());
				atualizarEstoque(venda.getItensVendidos());
			}
		}
	}
	
	private static void inserirProdutosVendidos(int idVenda, 
			List<ItemVenda> itensVenda) throws Exception {
		
		String sql = "INSERT INTO venda_produto (id_venda, id_prod, qtd_vend, preco_vend) "
				+ "VALUES (?, ?, ?, ?)";
		
		
		for(int i = 0; i < itensVenda.size(); i++) {
			ItemVenda itemVendido = itensVenda.get(i);
			
			try (PreparedStatement ps = DB.connect().prepareStatement(sql)){
				ps.setInt(1, idVenda);
				ps.setInt(2, itemVendido.getProduto().getId_prod());
				ps.setInt(3, itemVendido.getQtd_vend());
				ps.setFloat(4, itemVendido.getPreco_vend());
				ps.execute();
			}
		}
		
		
	}
	
	public static void atualizarEstoque(List<ItemVenda> itensVendidos) throws SQLException, Exception {
		String sql = "update produto set estoque = estoque - ? where id_prod = ?";
		
		for(int i = 0; i < itensVendidos.size(); i++) {
			ItemVenda itemVendido = itensVendidos.get(i);
			
			try (PreparedStatement ps = DB.connect().prepareStatement(sql)){
				ps.setInt(1, itemVendido.getQtd_vend());
				ps.setInt(2, itemVendido.getProduto().getId_prod());
				ps.execute();			
			}
		}
			
	}
	
	public static List<Venda> relatorio (LocalDate dataI, LocalDate dataF) throws SQLException, Exception{
		String sql = "select v.num_venda, v.data_venda, u.nome username, p.codigo,"
				+ " p.nome nome_prod, vp.preco_vend, vp.qtd_vend from venda v"
				+ " inner join usuario u on v.id_usuario = u.id"
				+ " inner join venda_produto vp on vp.id_venda = v.id_venda"
				+ " inner join produto p on p.id_prod = vp.id_prod"
				+ " where v.data_venda >= ? and v.data_venda <= ?";
		List<Venda> resultados = new ArrayList<Venda>();
		
		try(PreparedStatement ps = DB.connect().prepareStatement(sql)){
			ps.setDate(1, Date.valueOf(dataI));
			ps.setDate(2, Date.valueOf(dataF));
			
			ResultSet rs = ps.executeQuery();
			
			Venda venda = null;
			while(rs.next()) {
				int nVenda = rs.getInt("num_venda");
				if(venda == null || venda.getNum_venda() != nVenda) {
					venda = new Venda();
					venda.setNum_venda(nVenda);
					venda.setData_venda(rs.getDate("data_venda").toLocalDate());
					
					Usuario usuario = new Usuario();
					usuario.setNome(rs.getString("username"));
					
					venda.setUsuario(usuario);
					resultados.add(venda);
				}
				
				
				Produto produto = new Produto();
				produto.setCodigo(rs.getInt("codigo"));
				produto.setNome(rs.getString("nome_prod"));
				
				ItemVenda itemVenda = new ItemVenda();
				itemVenda.setPreco_vend(rs.getFloat("preco_vend"));
				itemVenda.setQtd_vend(rs.getInt("qtd_vend"));
				itemVenda.setProduto(produto);
				
				venda.getItensVendidos().add(itemVenda);
			}
		}
		return resultados;
	}
}
