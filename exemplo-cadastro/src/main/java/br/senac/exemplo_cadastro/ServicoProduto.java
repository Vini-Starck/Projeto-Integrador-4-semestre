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

@Path("produto")
public class ServicoProduto {
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void inserir(Produto produto) {
		try {
			DAOProduto.inserir(produto);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Produto> listar() throws Exception{
		
		try {
			return DAOProduto.listar();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public void atualizar(Produto produto) {
		try {
			DAOProduto.atualizar(produto);
		}catch(Exception e) {
			
		}
	}
	
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	public void excluir(@QueryParam("id") int id) {
		
		try {
			DAOProduto.excluir(id);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("O id "+id+" foi removido do banco de dados");
	}
	
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("pesquisar")
	public List<Produto> pesquisar(@QueryParam("nome")String nome) throws Exception{
		
		try {
			return DAOProduto.pesquisar(nome);
		} catch (Exception e){
			e.printStackTrace();
		}
		
		return null;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("obter")
	public Produto obter(@QueryParam("id")int id) throws Exception{
		
		try {
			return DAOProduto.obter(id);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
