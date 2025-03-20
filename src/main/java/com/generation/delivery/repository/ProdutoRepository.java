package com.generation.delivery.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.generation.delivery.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
	
	public List<Produto>findAllByNomeProdutoContainingIgnoreCase(@Param("nomeProduto") String nomeProduto);

	@Query("SELECT p FROM Produto p JOIN PedidoItem pi ON p.id = pi.produto.id " +
		       "WHERE p.restauranteId = :restauranteId " +
		       "GROUP BY p.id ORDER BY COUNT(pi.id) DESC")
		List<Produto> buscarMaisVendidosPorRestaurante(@Param("restauranteId") Long restauranteId);

}
