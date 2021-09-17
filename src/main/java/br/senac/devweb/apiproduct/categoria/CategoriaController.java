package br.senac.devweb.apiproduct.categoria;

import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("categorias")
@AllArgsConstructor
public class CategoriaController {

    private CategoriaService categoriaService;

    @PostMapping
    public ResponseEntity<CategoriaRepresentation.Detail> create(@RequestBody CategoriaRepresentation.Create createCategoria) {
        return ResponseEntity.ok(CategoriaRepresentation.Detail.from(this.categoriaService.save(createCategoria)));
    }

    @GetMapping
    public ResponseEntity<List<CategoriaRepresentation.Lista>> getAll() {
        BooleanExpression filter = QCategoria.categoria.status.eq(Categoria.Status.ATIVO);
        return ResponseEntity.ok(CategoriaRepresentation.Lista.from(categoriaService.getAll(filter)));
    }

    @DeleteMapping
    @RequestMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        this.categoriaService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
