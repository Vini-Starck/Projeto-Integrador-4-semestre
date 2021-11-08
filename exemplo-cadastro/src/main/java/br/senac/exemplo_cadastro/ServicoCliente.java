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

@Path("cliente")
public class ServicoCliente {
	
	private static List<Cliente> lista = new ArrayList<Cliente>();
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Cliente> listar(){
		
		try {
			return DAOCliente.listar();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void inserir(Cliente cliente) {
		System.out.println(cliente.getNome()+" foi adicionado ao banco de dados");
		try {
			DAOCliente.inserir(cliente);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("pesquisar")
	public List<Cliente> pesquisar(@QueryParam("nome")String nome){
		List<Cliente> resultados = new ArrayList<Cliente>();
		
		try {
			return DAOCliente.pesquisa(nome);
		} catch (Exception e){
			e.printStackTrace();
		}
		
		return null;
	}
	
	/*@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("pesquisar")
	public List<Cliente> pesquisar(@QueryParam("nome")String nome){
		List<Cliente> resultados = new ArrayList<Cliente>();
		
		for (int i = 0; i < lista.size(); i++) {
			Cliente cliente = lista.get(i);
			if(cliente.getNome().equals(nome)) {
				resultados.add(cliente);
			}
		}
		
		return resultados;
	}*/
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public void atualizar(Cliente cliente) {
		for (int i = 0; i < lista.size(); i++) {
			Cliente cliBusca = lista.get(i);
			if (cliBusca.getId() == cliente.getId()) {
				cliBusca.setNome(cliente.getNome());
				cliBusca.setCpf(cliente.getCpf());
				break;
			}
		}
	}
	
	
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	public void remove(@QueryParam("id") int id) {
		int pos = -1;
		int contador = 0;
		boolean encontrado = false;
		
		while (contador < lista.size() && !encontrado) {
			Cliente cliente = lista.get(contador);
			
			if(cliente.getId() == id) {
				pos = contador;
				encontrado = true;
			}
			contador++;
			
		}
		
		if(pos >= 0) {
			lista.remove(pos);
		}
	}
}
