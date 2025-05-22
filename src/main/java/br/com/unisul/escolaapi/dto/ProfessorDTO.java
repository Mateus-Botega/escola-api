package br.com.unisul.escolaapi.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class ProfessorDTO {

    private Long id;

    private LocalDateTime dataDeCriacao;

    private String nomeCompleto;

    private LocalDate dataDeNascimento;

    private List<TurmaDTO> turmas;

}
