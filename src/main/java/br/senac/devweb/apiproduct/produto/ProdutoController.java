package br.senac.devweb.apiproduct.produto;

import br.senac.devweb.apiproduct.categoria.Categoria;
import br.senac.devweb.apiproduct.categoria.CategoriaService;
import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/produtos")
@AllArgsConstructor
public class ProdutoController {

    private ProdutoService produtoService;
    private CategoriaService categoriaService;

    @PostMapping("/")
    public ResponseEntity<ProdutoRepresentation.Detail> post(@Valid @RequestBody ProdutoRepresentation.CreateOrUpdate create) {
        Categoria categoria = this.categoriaService.getById(create.getCategoria());

        return ResponseEntity.ok(ProdutoRepresentation.Detail.from(this.produtoService.save(create, categoria)));
    }

    @GetMapping("/")
    public ResponseEntity<List<ProdutoRepresentation.Lista>> getAll() {
        BooleanExpression filter = QProduto.produto.status.eq(Produto.Status.ATIVO);
        return ResponseEntity.ok(ProdutoRepresentation.Lista.from(produtoService.getAll(filter)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoRepresentation.Detail> get(@PathVariable("id") long id) {
        return ResponseEntity.ok(ProdutoRepresentation.Detail.from(produtoService.getById(id)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        this.produtoService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity put(@PathVariable("id") long id, @Valid @RequestBody ProdutoRepresentation.CreateOrUpdate createOrUpdate) {
        Categoria categoria = this.categoriaService.getById(createOrUpdate.getCategoria());

        return ResponseEntity.ok(ProdutoRepresentation.Detail.from(produtoService.update(id, createOrUpdate, categoria)));
    }
}
