package br.com.unisul.escolaapi.service;

import br.com.unisul.escolaapi.dto.ProfessorDTO;
import br.com.unisul.escolaapi.dto.TurmaDTO;
import br.com.unisul.escolaapi.entity.Professor;
import br.com.unisul.escolaapi.entity.Turma;
import br.com.unisul.escolaapi.repository.ProfessorRepository;
import jakarta.persistence.NoResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProfessorService {

    @Autowired
    private ProfessorRepository repository;

    public ProfessorDTO inserir(ProfessorDTO professorDTO) {
        validar(professorDTO);
        Professor novoProfessor = repository.save(new Professor(professorDTO));
        return new ProfessorDTO(novoProfessor);
    }

    public ProfessorDTO alterar(ProfessorDTO professorDTO) {
        if (professorDTO.getId() == null)
            throw new IllegalArgumentException("O id do professor é obrigatório para alteração");
        Professor professorExistente = repository.buscarPor(professorDTO.getId());
        if (professorExistente == null) {
            throw new IllegalArgumentException("O professor '" + professorDTO.getId() + "' não existe.");
        }
        professorExistente.setNomeCompleto(professorDTO.getNomeCompleto());
        professorExistente.setDataDeNascimento(professorDTO.getDataDeNascimento());
        professorExistente.setTurmas(professorDTO.getTurmas().stream().map(Turma::new).collect(Collectors.toList()));
        return new ProfessorDTO(repository.saveAndFlush(professorExistente));
    }

    public ProfessorDTO buscarPor(Long id) {
        Professor professor = Optional.ofNullable(repository.buscarPor(id))
                .orElseThrow(() -> new NoResultException("O professor '" + id + "' não existe."));
        ProfessorDTO dto = new ProfessorDTO(professor);
        for (TurmaDTO turma : dto.getTurmas()) {
            turma.setProfessores(null);
        }
        return dto;
    }

    public List<ProfessorDTO> listarPor(String nome) {
        List<Professor> professores = repository.listarPor("%" + nome + "%");
        return professores.stream().map(ProfessorDTO::new).toList();
    }

    public List<ProfessorDTO> listarPor(Turma turma) {
        List<Professor> professores = repository.listarPor(turma);
        return professores.stream().map(ProfessorDTO::new).toList();
    }

    private void validar(ProfessorDTO professorDTO) {
        if (professorDTO.getTurmas() == null || professorDTO.getTurmas().isEmpty()) {
            throw new IllegalArgumentException("A turma é obrigatória");
        }
    }

}
