package br.senac.devweb.apiproduct.categoria;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

public interface CategoriaRepository extends CrudRepository<Categoria, Integer>,  QuerydslPredicateExecutor<Categoria> {
}
