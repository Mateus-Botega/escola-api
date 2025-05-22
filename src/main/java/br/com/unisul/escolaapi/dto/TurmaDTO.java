package br.com.unisul.escolaapi.dto;

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

}
