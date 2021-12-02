package br.senac.exemplo_cadastro;

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

@Path("categoria")
public class ServicoCategoria {
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void inserir(Categoria categoria) {
		try {
			DAOCategoria.inserir(categoria);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Categoria> listar(){
		
		try {
			return DAOCategoria.listar();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public void atualizar(Categoria categoria) {
		try {
			DAOCategoria.atualizar(categoria);
		}catch(Exception e) {
			
		}
	}
	
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	public void excluir(@QueryParam("id") int id_cat) {
		
		try {
			DAOCategoria.excluir(id_cat);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("O id_cat "+id_cat+" foi removido da categoria");
	}
}


