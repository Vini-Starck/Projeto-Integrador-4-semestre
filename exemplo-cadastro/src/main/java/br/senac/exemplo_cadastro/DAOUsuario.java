package br.senac.exemplo_cadastro;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;



public class DAOUsuario {
	
	
	public static void inserir(Usuario usuario) throws Exception {
		String sql = "INSERT INTO usuario (nome, cpf, senha, data_nasc) VALUES(?, ?, ?, ?)";
		
		//try-with-resources
		try (PreparedStatement ps = DB.connect().prepareStatement(sql)) {
			ps.setString(1, usuario.getNome());
			ps.setString(2, usuario.getCpf());
			ps.setString(3, usuario.getSenha());
			ps.setDate(4, Date.valueOf(usuario.getData()));
			ps.execute();
		}
	}
	
	public static void atualizar(Usuario usuario) throws Exception {
		String sql = "UPDATE usuario SET nome = ?, cpf = ?, senha = ?, data_nasc = ? WHERE id = ?";
		
		try(PreparedStatement ps = DB.connect().prepareStatement(sql)) {
			ps.setString(1, usuario.getNome());
			ps.setString(2, usuario.getCpf());
			ps.setString(3, usuario.getSenha());
			ps.setDate(4, Date.valueOf(usuario.getData()));
			ps.setInt(5, usuario.getId());
			ps.execute();
		}
	}
	
	
	public static List<Usuario> listar() throws Exception {
		String sql = "SELECT nome, id FROM usuario";
		
		List<Usuario> resultados = new ArrayList<Usuario>();
		
		try (PreparedStatement ps = DB.connect().prepareStatement(sql)){
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Usuario usuario = new Usuario();
				
				usuario.setId(rs.getInt("id"));
				usuario.setNome(rs.getString("nome"));
				
				resultados.add(usuario);
			}
		}
		return resultados;
	}
	
	public static List<Usuario> pesquisa(String nome) throws Exception {
		String sql = "SELECT * FROM usuario WHERE nome LIKE ?";
		
		List<Usuario> resultados = new ArrayList<Usuario>();
		
		try (PreparedStatement ps = DB.connect().prepareStatement(sql)){
			ps.setString(1, "%"+ nome +"%");
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Usuario usuario = new Usuario();
				
				usuario.setId(rs.getInt("id"));
				usuario.setNome(rs.getString("nome"));
				
				resultados.add(usuario);
			}
		}
		return resultados;
		
		
			
	}
	
		public static void excluir(int id) throws Exception {
			String sql = "DELETE FROM usuario WHERE id = ?";
		
			try (PreparedStatement ps = DB.connect().prepareStatement(sql)) {
				ps.setInt(1, id);
				ps.execute();
			}
		}
		
		
		public static Usuario obter(int id) throws Exception {
			String sql = "SELECT * FROM usuario WHERE id = ? LIMIT 1";
			
			
			try (PreparedStatement ps = DB.connect().prepareStatement(sql)){
				ps.setInt(1, id);
				
				ResultSet rs = ps.executeQuery();
				
				if (rs.next()) {
					Usuario usuario = new Usuario();
					
					usuario.setId(rs.getInt("id"));
					usuario.setNome(rs.getString("nome"));
					usuario.setCpf(rs.getString("cpf"));
					usuario.setSenha(rs.getString("senha"));
					usuario.setData(rs.getDate("data_nasc").toLocalDate());
					
					return usuario;
				}
			}
			
			return null;
		}
		
		
	
	
	
	
	
}
