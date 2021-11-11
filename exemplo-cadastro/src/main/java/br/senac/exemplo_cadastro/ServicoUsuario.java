package br.senac.exemplo_cadastro;

import java.util.ArrayList;
import java.util.List;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;


@Path("usuario")
public class ServicoUsuario {

	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Usuario> listar(){
		
		try {
			return DAOUsuario.listar();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void inserir(Usuario usuario) {
		
		try {
			DAOUsuario.inserir(usuario);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(usuario.getUsuario()+" foi adicionado ao banco de dados");
	}
	
	
	
	
	
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("pesquisar")
	public List<Usuario> pesquisar(@QueryParam("usuario")String user){
		
		try {
			return DAOUsuario.pesquisa(user);
		} catch (Exception e){
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public void atualizar(Usuario usuario) {
		try {
			DAOUsuario.atualizar(usuario);
		}catch(Exception e) {
			
		}
	}
	
	
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	public void excluir(@QueryParam("id") int id) {
		
		try {
			DAOUsuario.excluir(id);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("O id "+id+" foi removido do banco de dados");
	}
}
