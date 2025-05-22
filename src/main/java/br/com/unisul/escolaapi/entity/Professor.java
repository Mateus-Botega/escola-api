package br.com.unisul.escolaapi.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "professores")
public class Professor {

    @Id
    @Column
    @GeneratedValue
    private Integer id;

    @Column(name = "dt_criacao")
    private LocalDateTime dataDeCriacao;

    @Column(name = "nome_completo")
    @NotNull(message = "O nome completo não pode ser nulo")
    private String nomeCompleto;

    @Column(name = "dt_nascimento")
    @NotNull(message = "A data de nascimento não pode ser nula")
    private String dataDeNascimento;

    @ManyToMany(mappedBy = "professores", fetch = FetchType.LAZY)
    private List<Turma> turmas;
}
