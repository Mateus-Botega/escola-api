package br.com.unisul.escolaapi.entity;

import br.com.unisul.escolaapi.dto.ProfessorDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Table(name = "professores")
public class Professor {

    @Id
    @Column
    @GeneratedValue
    private Long id;

    @Column(name = "dt_criacao")
    private LocalDateTime dataDeCriacao;

    @Column(name = "nome_completo")
    @NotNull(message = "O nome completo não pode ser nulo")
    private String nomeCompleto;

    @Column(name = "dt_nascimento")
    @NotNull(message = "A data de nascimento não pode ser nula")
    private LocalDate dataDeNascimento;

    @ManyToMany(mappedBy = "professores", fetch = FetchType.LAZY)
    private List<Turma> turmas;

    public Professor(ProfessorDTO dto) {
        this.id = dto.getId();
        this.dataDeCriacao = LocalDateTime.now();
        this.nomeCompleto = dto.getNomeCompleto();
        this.dataDeNascimento = dto.getDataDeNascimento();
        this.turmas = dto.getTurmas().stream().map(Turma::new).toList();
    }

}
