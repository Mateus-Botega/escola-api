package br.com.unisul.escolaapi.dto;

import br.com.unisul.escolaapi.entity.Professor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class ProfessorDTO {

    private Long id;

    private LocalDateTime dataDeCriacao;

    private String nomeCompleto;

    private LocalDate dataDeNascimento;

    private List<TurmaDTO> turmas;

    public ProfessorDTO(Professor professor){
        this.id = professor.getId();
        this.dataDeCriacao = professor.getDataDeCriacao();
        this.nomeCompleto = professor.getNomeCompleto();
        this.dataDeNascimento = professor.getDataDeNascimento();
        this.turmas = professor.getTurmas().stream().map(TurmaDTO::new).toList();
    }

}
