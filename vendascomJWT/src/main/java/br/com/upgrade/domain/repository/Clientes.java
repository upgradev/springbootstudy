package br.com.upgrade.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.upgrade.domain.entity.Cliente;

public interface Clientes extends JpaRepository<Cliente, Integer> {

//	hql
//	@Query(value = " select c from Cliente c where c.nome like :nome ")
	@Query(value = " select * from cliente c where c.nome like '%:nome%' ", nativeQuery = true)
	List<Cliente> encontrarPorNome(@Param("nome") String nome);

	@Query("delete from Cliente c where c.nome = :nome")
	@Modifying
	void deleteByNome(String nome);

	List<Cliente> findByNomeLike(String nome);

	boolean existsByNome(String nome);

	@Query(" select c from Cliente c left join fetch c.pedidos p where c.id =:id ")
	Cliente findClienteFetchPedidos(@Param("id") Integer id);
	
	

//@Repository
//public class Clientes {

//	private static String INSERT = "insert into cliente (nome) values (?) ";
//	private static String SELECT_ALL = "select * from cliente ";
//	private static String UPDATE = "update cliente set nome = ? where id = ? ";
//	private static String DELETE = "delete from cliente where id = ? ";
//
//	@Autowired
//	private JdbcTemplate jdbcTemplate;

//	@Autowired
//	private EntityManager entityManager;
//
//	@Transactional
//	public Cliente salvar(Cliente cliente) {
//		entityManager.persist(cliente);
//		return cliente;
//	}
//
////	public Cliente salvar(Cliente cliente) {
////		jdbcTemplate.update(INSERT, new Object[] { cliente.getNome() });
////		return cliente;
////	}
//
//	@Transactional
//	public Cliente atualizar(Cliente cliente) {
//		entityManager.merge(cliente);
//		return cliente;
//	}
//
////	public Cliente atualizar(Cliente cliente) {
////		jdbcTemplate.update(UPDATE, new Object[] { cliente.getNome(), cliente.getId() });
////		return cliente;
////	}
//
//	@Transactional
//	public void deletar(Cliente cliente) {
//		if(!entityManager.contains(cliente)) {
//			cliente = entityManager.merge(cliente);
//		}
//		entityManager.remove(cliente);
//
//	}
//	
////	public void deletar(Integer id) {
////		jdbcTemplate.update(DELETE, new Object[] { id });
////	}
//
//	
//	@Transactional(readOnly = true)
//	public List<Cliente> buscarPorNome(String nome) {
//		String jpql = "select c from Cliente c where c.nome like :nome ";
//		TypedQuery<Cliente> query = entityManager.createQuery(jpql, Cliente.class);
//		query.setParameter("nome", "%"+nome+"%");
//		return query.getResultList();
//	}
//
//	
////	@SuppressWarnings("deprecation")
////	public List<Cliente> buscarPorNome(String nome) {
////		return jdbcTemplate.query(SELECT_ALL.concat("where nome like ?"), new Object[] { "%" + nome + "%" },
////				obterClienteMapper());
////	}
//
//	@Transactional
//	public List<Cliente> obterTodos() {
//		return entityManager.createQuery("from Cliente", Cliente.class).getResultList();
//	}
//
////	public List<Cliente> obterTodos() {
////		return jdbcTemplate.query(SELECT_ALL, obterClienteMapper());
////	}
//
////	private RowMapper<Cliente> obterClienteMapper() {
////		return new RowMapper<Cliente>() {
////
////			@Override
////			public Cliente mapRow(ResultSet rs, int rowNum) throws SQLException {
////				Integer id = rs.getInt("id");
////				String nome = rs.getString("nome");
////				return new Cliente(id, nome);
////			}
////		};
////	}

}
