package br.com.unisul.escolaapi.dto;

import br.com.unisul.escolaapi.entity.Professor;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class ProfessorDTO {

    private Long id;

    @JsonFormat(pattern = "dd/MM/yyyy hh:mm")
    private LocalDateTime dataDeCriacao;

    private String nomeCompleto;

    @JsonFormat(pattern = "dd/MM/yyyy")
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
