package br.senac.devweb.apiproduct.categoria;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaService {

    private CategoriaRepository categoriaRepository;

    public Categoria save(CategoriaRepresentation.CreateCategoria createCategoria) {
        return this.categoriaRepository.save(Categoria.builder()
                .descricao(createCategoria.getDescricao())
                .status(Categoria.Status.ATIVO)
                .build());
    }
}
