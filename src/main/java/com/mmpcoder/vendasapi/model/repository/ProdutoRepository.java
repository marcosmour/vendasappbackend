package com.mmpcoder.vendasapi.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mmpcoder.vendasapi.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{

}
