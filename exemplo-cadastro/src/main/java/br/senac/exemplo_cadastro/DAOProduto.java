package br.senac.exemplo_cadastro;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DAOProduto {
	
	public static void inserir(Produto produto) throws Exception {
		String sql = "INSERT INTO produto (nome, codigo, preco, fabricante, descricao, estoque, id_categoria) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?)";
		
		try (PreparedStatement ps = DB.connect().prepareStatement(sql)){
			ps.setString(1, produto.getNome());
			ps.setInt(2, produto.getCodigo());
			ps.setFloat(3, produto.getPreco());
			ps.setString(4, produto.getFabricante());
			ps.setString(5, produto.getDescricao());
			ps.setInt(6, produto.getEstoque());
			ps.setInt(7, produto.getCategoria().getId_cat());
			
			ps.execute();
		}
	}
	
	public static List<Produto> listar() throws Exception {
		String sql = "select * from produto"
				+ "	inner join categoria on produto.id_categoria = categoria.id_cat";
		
		List<Produto> resultados = new ArrayList<Produto>();
		
		try (PreparedStatement ps = DB.connect().prepareStatement(sql)){
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Produto produto = new Produto();
				
				produto.setId_prod(rs.getInt("id_prod"));
				produto.setNome(rs.getString("nome"));
				produto.setCodigo(rs.getInt("codigo"));
				produto.setPreco(rs.getFloat("preco"));
				produto.setFabricante(rs.getString("fabricante"));
				produto.setDescricao(rs.getString("descricao"));
				produto.setEstoque(rs.getInt("estoque"));
				
				Categoria cat = new Categoria();
				cat.setId_cat(rs.getInt("id_cat"));
				cat.setNome(rs.getString("categoria.nome"));
				
				produto.setCategoria(cat);
				
				resultados.add(produto);
			}
		}
		return resultados;
	}
	
	public static void atualizar(Produto produto) throws Exception {
		String sql = "UPDATE produto SET nome = ?, codigo = ?, preco = ?, fabricante = ?, "
				+ "descricao = ?, estoque = ?, id_categoria = ? WHERE id_prod = ?";
		
		try(PreparedStatement ps = DB.connect().prepareStatement(sql)) {
			
			ps.setString(1, produto.getNome());
			ps.setInt(2, produto.getCodigo());
			ps.setFloat(3, produto.getPreco());
			ps.setString(4, produto.getFabricante());
			ps.setString(5, produto.getDescricao());
			ps.setInt(6, produto.getEstoque());
			ps.setInt(7, produto.getCategoria().getId_cat());
			ps.setInt(8, produto.getId_prod());
			ps.execute();
		}
	}
	
	public static void excluir(int id) throws Exception {
		String sql = "DELETE FROM produto WHERE id_prod = ?";
	
		try (PreparedStatement ps = DB.connect().prepareStatement(sql)) {
			ps.setInt(1, id);
			ps.execute();
		}
	}
	
	public static List<Produto> pesquisar(String pesquisa) throws Exception {
		String sql = "select * from produto p"
				+ "	inner join categoria c on p.id_categoria = c.id_cat"
				+ " where p.nome like ?";
		
		List<Produto> resultados = new ArrayList<Produto>();
		
		try(PreparedStatement ps = DB.connect().prepareStatement(sql)){
			ps.setString(1, "%" + pesquisa + "%");
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				Produto p = new Produto();
				p.setId_prod(rs.getInt("id_prod"));
				p.setNome(rs.getString("nome"));
				p.setCodigo(rs.getInt("codigo"));
				p.setPreco(rs.getFloat("preco"));
				p.setFabricante(rs.getString("fabricante"));
				p.setDescricao(rs.getString("descricao"));
				p.setEstoque(rs.getInt("estoque"));
				Categoria cat = new Categoria();
				cat.setId_cat(rs.getInt("c.id_cat"));
				cat.setNome(rs.getString("c.nome"));
				
				p.setCategoria(cat);
				
				resultados.add(p);
			}
		}
		return resultados;
		
	}
	
	
	public static Produto obter(int id) throws Exception {
		String sql = "SELECT * FROM produto p inner join categoria c "
				+ "on p.id_categoria = c.id_cat "
				+ "WHERE p.id_prod = ? LIMIT 1";
		
		
		try (PreparedStatement ps = DB.connect().prepareStatement(sql)){
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				Produto produto = new Produto();
				
				produto.setId_prod(rs.getInt("id_prod"));
				produto.setNome(rs.getString("nome"));
				produto.setCodigo(rs.getInt("codigo"));
				produto.setPreco(rs.getFloat("preco"));
				produto.setFabricante(rs.getString("fabricante"));
				produto.setDescricao(rs.getString("descricao"));
				Categoria cat = new Categoria();
				cat.setId_cat(rs.getInt("id_cat"));
				cat.setNome(rs.getString("nome"));
				
				produto.setCategoria(cat);
				
				
				return produto;
			}
		}
		
		return null;
	}
}
