package br.senac.devweb.apiproduct.categoria;

import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("categorias")
@AllArgsConstructor
public class CategoriaController {

    private CategoriaService categoriaService;

    @PostMapping("/")
    public ResponseEntity<CategoriaRepresentation.Detail> post(@Valid @RequestBody CategoriaRepresentation.CreateOrUpdate categoria) {
        return ResponseEntity.ok(CategoriaRepresentation.Detail.from(this.categoriaService.save(categoria)));
    }

    @GetMapping("/")
    public ResponseEntity<List<CategoriaRepresentation.Lista>> getAll() {
        BooleanExpression filter = QCategoria.categoria.status.eq(Categoria.Status.ATIVO);
        return ResponseEntity.ok(CategoriaRepresentation.Lista.from(categoriaService.getAll(filter)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaRepresentation.Detail> get(@PathVariable("id") long id) {
        return ResponseEntity.ok(CategoriaRepresentation.Detail.from(categoriaService.getById(id)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        this.categoriaService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity put(@PathVariable("id") long id, @Valid @RequestBody CategoriaRepresentation.CreateOrUpdate createOrUpdate) {
        return ResponseEntity.ok(CategoriaRepresentation.Detail.from(categoriaService.update(id, createOrUpdate)));
    }
}
