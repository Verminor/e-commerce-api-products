package br.senac.devweb.apiproduct.categoria;

import br.senac.devweb.apiproduct.exceptions.NotFoundException;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoriaService {

    private CategoriaRepository categoriaRepository;

    public Categoria save(CategoriaRepresentation.CreateOrUpdate createCategoria) {
        return this.categoriaRepository.save(Categoria.builder()
                .descricao(createCategoria.getDescricao())
                .status(Categoria.Status.ATIVO)
                .build());
    }

    public List<Categoria> getAll(Predicate filter) {
        return this.categoriaRepository.findAll(filter);
    }

    public void delete(Long id) {
        Categoria categoria = this.getById(id);
        categoria.setStatus(Categoria.Status.INATIVO);
        this.categoriaRepository.save(categoria);
    }

    public Categoria getById(long id) {
        BooleanExpression filter = QCategoria.categoria.id.eq(id).and(QCategoria.categoria.status.eq(Categoria.Status.ATIVO));
        return this.categoriaRepository.findOne(filter).orElseThrow(() -> new NotFoundException("Categoria não encontrada"));
    }

    public Categoria update(long id, CategoriaRepresentation.CreateOrUpdate createOrUpdate) {
        Categoria categoria = this.getById(id);
        categoria.setDescricao(createOrUpdate.getDescricao());
        return this.categoriaRepository.save(categoria);
    }
}
