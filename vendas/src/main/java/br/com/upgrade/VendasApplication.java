package br.com.upgrade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import br.com.upgrade.domain.entity.Cliente;
import br.com.upgrade.domain.repository.Clientes;

@SpringBootApplication
public class VendasApplication {
	
//	@Bean
//	public CommandLineRunner commandLineRunner(@Autowired Clientes clientes) {
//		return args ->{
//			Cliente c = new Cliente(null, "fulano");
//			clientes.save(c);
//		};
//	}
//	
	
	public static void main(String[] args) {
		SpringApplication.run(VendasApplication.class, args);
	}
	

//	@Bean
//	public CommandLineRunner init(@Autowired Clientes clientes, @Autowired Pedidos pedidos) {
//		return args -> {
//			System.out.println("Salvando Clientes");
//			Cliente fulano = new Cliente("Cleison");
//			clientes.save(fulano);
//			
//			Pedido p = new Pedido();
//			p.setCliente(fulano);
//			p.setDataPedido(LocalDate.now());
//			p.setTotal(BigDecimal.valueOf(100));
//			
//			pedidos.save(p);
//			
//			
//			
//			
////			Cliente cliente = clientes.findClienteFetchPedidos(fulano.getId());
////			System.out.println(cliente);
////			System.out.println(cliente.getPedidos());
//			
//			pedidos.findByCliente(fulano).forEach(System.out::println);
//			
//			
////			System.out.println("Atualizando Clientes");
////			todosClientes.forEach(c -> {
////				c.setNome(c.getNome() + " Atualizado");
////				clientes.save(c);
////			});
////			
////			todosClientes =  clientes.findAll();
////			todosClientes.forEach(System.out::println);
////			
////			System.out.println("Buscando Clientes");
////			clientes.findByNomeLike("Cle").forEach(System.out::println);
////			
////			System.out.println("Deletando Clientes");
////			clientes.findAll().forEach(c -> {
////				clientes.delete(c);
////			});
////			
////			todosClientes =  clientes.findAll();
////			if(todosClientes.isEmpty()) {
////				System.out.println("Nenhum Cliente encontrado");
////			}
////			else {
////				todosClientes.forEach(System.out::println);
////			}
//			
//			
////			clientes.salvar(new Cliente("Cleison"));
////			clientes.salvar(new Cliente("Ana"));
//			
////			List<Cliente> todosClientes =  clientes.obterTodos();
////			todosClientes.forEach(System.out::println);
////			
////			System.out.println("Atualizando Clientes");
////			todosClientes.forEach(c -> {
////				c.setNome(c.getNome() + " Atualizado");
////				clientes.atualizar(c);
////			});
////			
////			todosClientes =  clientes.obterTodos();
////			todosClientes.forEach(System.out::println);
////			
////			System.out.println("Buscando Clientes");
////			clientes.buscarPorNome("Cle").forEach(System.out::println);
////			
////			System.out.println("Deletando Clientes");
////			clientes.obterTodos().forEach(c -> {
////				clientes.deletar(c);
////			});
////			
////			todosClientes =  clientes.obterTodos();
////			if(todosClientes.isEmpty()) {
////				System.out.println("Nenhum Cliente encontrado");
////			}
////			else {
////				todosClientes.forEach(System.out::println);
////			}
//			
//			
//			
//		};
//
//	}

	

}
