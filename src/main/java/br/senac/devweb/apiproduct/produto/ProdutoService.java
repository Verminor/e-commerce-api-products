package br.senac.devweb.apiproduct.produto;

import br.senac.devweb.apiproduct.categoria.Categoria;
import br.senac.devweb.apiproduct.exceptions.NotFoundException;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProdutoService {

    private ProdutoRepository produtoRepository;

    public Produto save(ProdutoRepresentation.CreateOrUpdate createOrUpdate, Categoria categoria) {
        return this.produtoRepository.save(Produto.builder()
                .nome(createOrUpdate.getNome())
                .descricao(createOrUpdate.getDescricao())
                .complemento(createOrUpdate.getComplemento().isEmpty() ? "" : createOrUpdate.getComplemento())
                .fabricante(createOrUpdate.getFabricante())
                .fornecedor(createOrUpdate.getFornecedor().isEmpty() ? "" : createOrUpdate.getFornecedor())
                .qtde(createOrUpdate.getQtde())
                .valor(createOrUpdate.getValor())
                .unidadeMedida(createOrUpdate.getUnidadeMedida())
                .categoria(categoria)
                .status(Produto.Status.ATIVO)
                .build());
    }

    public List<Produto> getAll(Predicate filter) {
        return this.produtoRepository.findAll(filter);
    }

    public void delete(long id) {
        Produto produto = this.getById(id);
        produto.setStatus(Produto.Status.INATIVO);
        this.produtoRepository.save(produto);
    }

    public Produto getById(long id) {
        BooleanExpression filter = QProduto.produto.id.eq(id).and(QProduto.produto.status.eq(Produto.Status.ATIVO));
        return this.produtoRepository.findOne(filter).orElseThrow(() -> new NotFoundException("Produto não encontrado"));
    }

    public Produto update(long id, ProdutoRepresentation.CreateOrUpdate createOrUpdate, Categoria categoria) {
        Produto produto = this.getById(id).toBuilder()
                .nome(createOrUpdate.getNome())
                .descricao(createOrUpdate.getDescricao())
                .complemento(createOrUpdate.getComplemento().isEmpty() ? "" : createOrUpdate.getComplemento())
                .fabricante(createOrUpdate.getFabricante())
                .fornecedor(createOrUpdate.getFornecedor().isEmpty() ? "" : createOrUpdate.getFornecedor())
                .qtde(createOrUpdate.getQtde())
                .valor(createOrUpdate.getValor())
                .unidadeMedida(createOrUpdate.getUnidadeMedida())
                .categoria(categoria)
                .build();
        return this.produtoRepository.save(produto);
    }
}
