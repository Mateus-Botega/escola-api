package br.com.unisul.escolaapi.dto;

import br.com.unisul.escolaapi.entity.Aluno;
import com.fasterxml.jackson.annotation.JsonFormat;
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

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime dataDeCriacao;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataDeNascimento;

    private String matricula;

    private TurmaDTO turma;

}
