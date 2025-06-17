package br.com.unisul.escolaapi.dto;

import br.com.unisul.escolaapi.entity.Turma;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class TurmaDTO {

    private Long id;

    private String nome;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime dataDeCriacao;

    private String turno;

    private List<ProfessorDTO> professores;

    private List<AlunoDTO> alunos;

    public TurmaDTO(Turma turma) {
        this.id = turma.getId();
        this.nome = turma.getNome();
        this.dataDeCriacao = turma.getDataDeCriacao();
        this.turno = String.valueOf(turma.getTurno());
        this.professores = new ArrayList<>();
        this.alunos = new ArrayList<>();
    }

}
