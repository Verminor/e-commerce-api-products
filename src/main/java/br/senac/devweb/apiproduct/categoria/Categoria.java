package br.senac.devweb.apiproduct.categoria;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @NotNull(message = "O campo descrição não pode ser nulo")
    @NotEmpty(message = "O campo descrição não pode ser vazia")
    @Column
    @Size(max = 30, min = 1, message = "A descrição deve conter de 1 a 30 caracteres")
    private String descricao;

    @NotNull(message = "O campo status não pode ser nulo")
    @Column
    private Status status;

    public enum Status {
        ATIVO,
        INATIVO
    }
}
