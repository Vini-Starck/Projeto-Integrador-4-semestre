package br.senac.exemplo_cadastro;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class DAOCategoria {

	
	public static void inserir(Categoria categoria) throws Exception {
		String sql = "INSERT INTO categoria (nome) VALUES (?)";
		
		try (PreparedStatement ps = DB.connect().prepareStatement(sql)){
			ps.setString(1, categoria.getNome());
			
			ps.execute();
		}
	}
	
	public static List<Categoria> listar() throws Exception {
		String sql = "SELECT * FROM categoria";
		List<Categoria> resultados = new ArrayList<Categoria>();
		
		
		try (PreparedStatement ps = DB.connect().prepareStatement(sql)){
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				Categoria categoria = new Categoria();
				
				categoria.setId_cat(rs.getInt("id_cat"));
				categoria.setNome(rs.getString("nome"));
				
				resultados.add(categoria);
			}
		}
		
		return resultados;
	}
	
	public static void atualizar(Categoria categoria) throws Exception {
		String sql = "UPDATE categoria SET nome = ? WHERE id_cat = ?";
		
		try(PreparedStatement ps = DB.connect().prepareStatement(sql)) {
			ps.setString(1, categoria.getNome());
			ps.setInt(2, categoria.getId_cat());
			
			ps.execute();
		}
	}
	
	public static void excluir(int id_cat) throws Exception {
		String sql = "DELETE FROM categoria WHERE id_cat = ?";
	
		try (PreparedStatement ps = DB.connect().prepareStatement(sql)) {
			ps.setInt(1, id_cat);
			ps.execute();
		}
	}
}
