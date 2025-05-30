package br.com.unisul.escolaapi.dto;

import br.com.unisul.escolaapi.entity.Turma;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class TurmaDTO {

    private Long id;
    private String nome;
    private LocalDateTime dataDeCriacao;
    private String turno;
    private List<ProfessorDTO> professores;
    private List<AlunoDTO> alunos;

    public TurmaDTO(Turma turma) {
        this.id = turma.getId();
        this.nome = turma.getNome();
        this.dataDeCriacao = turma.getDataDeCriacao();
        this.turno = String.valueOf(turma.getTurno());
        this.professores = turma.getProfessores().stream().map(ProfessorDTO::new).toList();
        this.alunos = turma.getAlunos().stream().map(AlunoDTO::new).toList();
    }

}
