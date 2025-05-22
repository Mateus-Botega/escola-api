package br.com.unisul.escolaapi.dto;

import br.com.unisul.escolaapi.entity.Aluno;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class AlunoDTO {

    public AlunoDTO(Aluno aluno) {
        this.id = aluno.getId();
        this.nomeCompleto = aluno.getNomeCompleto();
        this.dataDeCriacao = aluno.getDataDeCriacao();
        this.dataDeNascimento = aluno.getDataDeNascimento();
        this.matricula = aluno.getMatricula();
        this.turma = new TurmaDTO(aluno.getTurma());
    }

    private Long id;

    private String nomeCompleto;

    private LocalDateTime dataDeCriacao;

    private LocalDate dataDeNascimento;

    private String matricula;

    private TurmaDTO turma;

}
