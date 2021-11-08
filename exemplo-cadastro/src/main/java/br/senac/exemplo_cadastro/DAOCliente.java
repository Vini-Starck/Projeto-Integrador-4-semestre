package br.senac.exemplo_cadastro;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class DAOCliente {
	
	
	public static void inserir(Cliente cliente) throws Exception {
		String sql = "INSERT INTO cliente (nome, cpf) VALUES(?, ?)";
		
		//try-with-resources
		try (PreparedStatement ps = DB.connect().prepareStatement(sql)) {
			ps.setString(1, cliente.getNome());
			ps.setString(2, cliente.getCpf());
			
			ps.execute();
		}
	}
	
	
	public static List<Cliente> listar() throws Exception {
		String sql = "SELECT * FROM cliente";
		
		List<Cliente> resultados = new ArrayList<Cliente>();
		
		try (PreparedStatement ps = DB.connect().prepareStatement(sql)){
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Cliente cliente = new Cliente();
				
				cliente.setId(rs.getInt("id"));
				cliente.setNome(rs.getString("nome"));
				cliente.setCpf(rs.getString("cpf"));
				
				resultados.add(cliente);
			}
		}
		return resultados;
	}
	
	public static List<Cliente> pesquisa(String nome) throws Exception {
		String sql = "SELECT * FROM cliente WHERE nome = ?";
		
		List<Cliente> resultados = new ArrayList<Cliente>();
		
		try (PreparedStatement ps = DB.connect().prepareStatement(sql)){
			ps.setString(1, nome);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Cliente cliente = new Cliente();
				
				cliente.setId(rs.getInt("id"));
				cliente.setNome(rs.getString("nome"));
				cliente.setCpf(rs.getString("cpf"));
				
				resultados.add(cliente);
			}
		}
		return resultados;
	}
	
}
