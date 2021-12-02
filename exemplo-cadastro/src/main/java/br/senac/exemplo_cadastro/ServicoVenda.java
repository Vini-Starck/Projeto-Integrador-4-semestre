package br.senac.exemplo_cadastro;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

@Path("venda")
public class ServicoVenda {
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void fazerVenda(Venda venda) {
		
		try {
			DAOVenda.fazerVenda(venda);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<Venda> relatorio(@QueryParam("dataI") String dataInicial,
			@QueryParam("dataF") String dataFinal){
		
		
		try {
			return DAOVenda.relatorio(LocalDate.parse(dataInicial), LocalDate.parse(dataFinal));
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
}
