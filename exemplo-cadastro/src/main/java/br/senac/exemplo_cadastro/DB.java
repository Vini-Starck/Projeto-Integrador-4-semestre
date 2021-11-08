package br.senac.exemplo_cadastro;

import java.sql.Connection;
import java.sql.DriverManager;


public class DB {
	
	public static Connection connect() throws Exception {
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		return DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/projeto",
				"root", "starck0147");
	}
	
	public static void main(String[] args) throws Exception {
		connect();
		System.out.println("Conectado");
	}
	
	
	
}
