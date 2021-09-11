package br.senac.devweb.apiproduct.categoria;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("categoria")
@AllArgsConstructor
public class CategoriaController {

    private CategoriaService categoriaService;

    @PostMapping
    public ResponseEntity<CategoriaRepresentation.Detail> createCategoria(@RequestBody CategoriaRepresentation.CreateCategoria createCategoria) {
        return ResponseEntity.ok(CategoriaRepresentation.Detail.from(this.categoriaService.save(createCategoria)));
    }
}
