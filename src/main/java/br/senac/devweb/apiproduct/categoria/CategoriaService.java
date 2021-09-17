package br.senac.devweb.apiproduct.categoria;

import com.querydsl.core.types.Predicate;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoriaService {

    private CategoriaRepository categoriaRepository;

    public Categoria save(CategoriaRepresentation.Create createCategoria) {
        return this.categoriaRepository.save(Categoria.builder()
                .descricao(createCategoria.getDescricao())
                .status(Categoria.Status.ATIVO)
                .build());
    }

    public List<Categoria> getAll(Predicate filter) {
        return this.categoriaRepository.findAll(filter);
    }

    public void delete(Long id) {
        Categoria categoria = this.categoriaRepository.findById(id).get();
        categoria.setStatus(Categoria.Status.INATIVO);
        this.categoriaRepository.save(categoria);
    }
}
