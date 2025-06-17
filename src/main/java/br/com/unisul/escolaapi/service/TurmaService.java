package br.com.unisul.escolaapi.service;

import br.com.unisul.escolaapi.dto.TurmaDTO;
import br.com.unisul.escolaapi.entity.Turma;
import br.com.unisul.escolaapi.entity.enums.Turno;
import br.com.unisul.escolaapi.repository.TurmaRepository;
import jakarta.persistence.NoResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TurmaService {

    @Autowired
    private TurmaRepository turmaRepository;

    @Autowired
    private ProfessorService professorService;

    @Autowired
    private AlunoService alunoService;

    public List<TurmaDTO> listarPor(String filtro) {
        List<Turma> turmasEncontradas = turmaRepository.listarPor("%" + filtro + "%");
        List<TurmaDTO> turmasDTO = turmasEncontradas.stream().map(TurmaDTO::new).toList();
        for (TurmaDTO turmaDTO : turmasDTO) {
            Turma turma = new Turma(turmaDTO);
            turmaDTO.setAlunos(alunoService.listarPor(turma));
            turmaDTO.setProfessores(professorService.listarPor(turma));
        }
        return turmasDTO;
    }

    public TurmaDTO buscarPor(Long id) {
        Turma turma = Optional.ofNullable(turmaRepository.buscarPor(id))
                .orElseThrow(() -> new NoResultException("A turma '" + id + "' não existe."));
        TurmaDTO turmaDTO = new TurmaDTO(turma);
        turmaDTO.setAlunos(alunoService.listarPor(turma));
        turmaDTO.setProfessores(professorService.listarPor(turma));
        return turmaDTO;
    }

    public TurmaDTO inserir(TurmaDTO turmaDTO) {
        validar(turmaDTO);
        return new TurmaDTO(turmaRepository.save(new Turma(turmaDTO)));
    }

    public TurmaDTO alterar(TurmaDTO turmaDTO) {
        if (turmaDTO.getId() == null)
            throw new IllegalArgumentException("O id da turma é obrigatório para alteração");
        Turma turmaExistente = turmaRepository.buscarPor(turmaDTO.getId());
        if (turmaExistente == null) {
            throw new IllegalArgumentException("A turma '" + turmaDTO.getId() + "' não existe.");
        }
        validar(turmaDTO);
        turmaExistente.setNome(turmaDTO.getNome());
        turmaExistente.setTurno(Turno.valueOf(turmaDTO.getTurno()));
        return new TurmaDTO(turmaRepository.saveAndFlush(turmaExistente));
    }

    private void validar(TurmaDTO turmaDTO) {
        try {
            Turno.valueOf(turmaDTO.getTurno());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("O turno '" + turmaDTO.getTurno() + "' é inválido.");
        }

        if (turmaDTO.getAlunos() != null && !turmaDTO.getAlunos().isEmpty()) {
            throw new IllegalArgumentException("Os alunos da turma devem estar vazios");
        }

        if (turmaDTO.getProfessores() != null && !turmaDTO.getProfessores().isEmpty()) {
            throw new IllegalArgumentException("Os professores devem estar vazios");
        }
    }

}
