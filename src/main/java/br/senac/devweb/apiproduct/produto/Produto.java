package br.senac.devweb.apiproduct.produto;

import br.senac.devweb.apiproduct.categoria.Categoria;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Entity
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "O campo nome não pode ser nulo")
    @Size(min = 1, max = 30, message = "O campo nome deve ter entre 1 e 30")
    @Column
    private String nome;

    @NotNull(message = "O campo descrição não pode ser nulo")
    @Size(min = 1, max = 255, message = "O campo descrição deve ter entre 1 e 255")
    @Column
    private String descricao;

    @Column
    private String complemento;

    @NotNull(message = "O campo valor não pode ser nulo")
    @Column
    private Double valor;

    @NotNull(message = "O campo unidade de medida não pode ser nulo")
    @Column(name = "unidade_medida")
    private unidadeMedida unidadeMedida;

    @NotNull(message = "O campo quantidade não pode ser nulo")
    @Column
    private Double qtde;

    @NotNull(message = "O campo fabricante não pode ser nulo")
    @Size(min = 1, max = 100, message = "O campo fabricante deve ter entre 1 e 100")
    @Column
    private String fabricante;

    @Column
    private String fornecedor;

    @NotNull(message = "O campo status não pode ser nulo")
    @Column
    private Status status;

    @NotNull(message = "A categoria é obrigatória")
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Categoria.class)
    @JoinColumn(name = "id_categoria")
    private Categoria categoria;

    public enum unidadeMedida {
        UN,
        KG,
        ML,
        TN,
        MT,
        MT2,
        MT3,
        LT
    }

    public enum Status {
        ATIVO,
        INATIVO
    }
}
