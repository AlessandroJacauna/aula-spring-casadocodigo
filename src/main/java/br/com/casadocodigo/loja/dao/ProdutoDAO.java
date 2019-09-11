//Classe responsável pelo persistência dos dados, no caso o produto cadastrado
package br.com.casadocodigo.loja.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.casadocodigo.loja.models.Produto;

@Repository //anotation para que o spring reconheça a classe e que ela é gerenciada pelo spring
@Transactional
public class ProdutoDAO {
	
	@PersistenceContext
	private EntityManager manager;
	
	public void gravar(Produto produto){
		manager.persist(produto);
		
	}

	public List<Produto> listar() {
		return manager.createQuery("select p from Produto p", Produto.class).getResultList();
	}

	public Produto find(Integer id) {
		return manager.createQuery("select distinct(p) from Produto p "
				+ "join fetch p.precos preco where p.id = :id",Produto.class).setParameter("id", id)
				.getSingleResult();
	}
	
}
