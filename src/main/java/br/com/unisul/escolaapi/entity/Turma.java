package br.com.unisul.escolaapi.entity;

import br.com.unisul.escolaapi.dto.TurmaDTO;
import br.com.unisul.escolaapi.entity.enums.Turno;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Table(name = "turmas")
public class Turma {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    @NotBlank(message = "O nome não deve ser um espaço em branco")
    private String nome;

    @Column(name = "turno")
    @Enumerated(EnumType.STRING)
    @NotNull(message = "O turno é obrigatório")
    private Turno turno;

    @Column(name = "dt_criacao")
    private LocalDateTime dataDeCriacao;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "turma")
    private List<Aluno> alunos;

    @ManyToMany(mappedBy = "turmas", fetch = FetchType.LAZY)
    private List<Professor> professores;

    public Turma(TurmaDTO dto) {
        this.id = dto.getId();
        this.nome = dto.getNome();
        if (dto.getTurno() != null) this.turno = Turno.valueOf(dto.getTurno());
        this.dataDeCriacao = LocalDateTime.now();
        this.alunos = new ArrayList<>();
        this.professores = new ArrayList<>();
    }

}
