package br.com.unisul.escolaapi.entity;

import br.com.unisul.escolaapi.dto.AlunoDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@Table(name = "alunos")
public class Aluno {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_completo")
    @NotNull(message = "O nome completo não pode ser nulo")
    private String nomeCompleto;

    @Column(name = "dt_criacao")
    private LocalDateTime dataDeCriacao;

    @Column(name = "dt_nascimento")
    @NotNull(message = "A data de nascimento não pode ser nula")
    private LocalDate dataDeNascimento;

    @Column(name = "matricula")
    @NotNull(message = "A matrícula não pode ser nula")
    private String matricula;

    @JoinColumn(name = "id_turma")
    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull(message = "A turma não pode ser nula")
    private Turma turma;

    public Aluno(AlunoDTO dto) {
        this.id = dto.getId();
        this.nomeCompleto = dto.getNomeCompleto();
        this.dataDeCriacao = LocalDateTime.now();
        this.dataDeNascimento = dto.getDataDeNascimento();
        this.matricula = dto.getMatricula();
        this.turma = new Turma(dto.getTurma());
    }

}
