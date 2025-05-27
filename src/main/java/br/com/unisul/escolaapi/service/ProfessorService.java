package br.com.unisul.escolaapi.service;

import br.com.unisul.escolaapi.dto.ProfessorDTO;
import br.com.unisul.escolaapi.entity.Professor;
import br.com.unisul.escolaapi.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfessorService {

    @Autowired
    private ProfessorRepository repository;

    public ProfessorDTO inserir(ProfessorDTO professorDTO) {
        Professor novoProfessor = repository.save(new Professor(professorDTO));
        return new ProfessorDTO(novoProfessor);
    }

    public ProfessorDTO alterar(ProfessorDTO professorDTO) {
        Professor professorSalvo = repository.save(new Professor(professorDTO));
        return new ProfessorDTO(professorSalvo);
    }

    public ProfessorDTO buscarPor(Long id) {
        Professor professor = repository.buscarPor(id);
        return new ProfessorDTO(professor);
    }

    public List<ProfessorDTO> listarPor(String nome) {
        String filtroParaNome = "%" + nome + "%";
        List<Professor> professores = repository.listarPor(filtroParaNome);
        return professores.stream().map(ProfessorDTO::new).toList();
    }

}
