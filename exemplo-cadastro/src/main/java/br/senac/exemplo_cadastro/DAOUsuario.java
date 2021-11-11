package br.senac.exemplo_cadastro;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class DAOUsuario {
	
	
	public static void inserir(Usuario usuario) throws Exception {
		String sql = "INSERT INTO usuario (usuario, senha) VALUES(?, ?)";
		
		//try-with-resources
		try (PreparedStatement ps = DB.connect().prepareStatement(sql)) {
			ps.setString(1, usuario.getUsuario());
			ps.setString(2, usuario.getSenha());
			
			ps.execute();
		}
	}
	
	public static void atualizar(Usuario usuario) throws Exception {
		String sql = "UPDATE usuario SET usuario = ?, senha = ? WHERE id = ?";
		
		try(PreparedStatement ps = DB.connect().prepareStatement(sql)) {
			ps.setString(1, usuario.getUsuario());
			ps.setString(2, usuario.getSenha());
			ps.setInt(3, usuario.getId());
			ps.execute();
		}
	}
	
	
	public static List<Usuario> listar() throws Exception {
		String sql = "SELECT * FROM cliente";
		
		List<Usuario> resultados = new ArrayList<Usuario>();
		
		try (PreparedStatement ps = DB.connect().prepareStatement(sql)){
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Usuario usuario = new Usuario();
				
				usuario.setId(rs.getInt("id"));
				usuario.setUsuario(rs.getString("usuario"));
				usuario.setSenha(rs.getString("senha"));
				
				resultados.add(usuario);
			}
		}
		return resultados;
	}
	
	public static List<Usuario> pesquisa(String user) throws Exception {
		String sql = "SELECT * FROM usuario WHERE usuario = ?";
		
		List<Usuario> resultados = new ArrayList<Usuario>();
		
		try (PreparedStatement ps = DB.connect().prepareStatement(sql)){
			ps.setString(1, user);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Usuario usuario = new Usuario();
				
				usuario.setId(rs.getInt("id"));
				usuario.setUsuario(rs.getString("usuario"));
				usuario.setSenha(rs.getString("senha"));
				
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
		
		
	
	
	
	
	
}
