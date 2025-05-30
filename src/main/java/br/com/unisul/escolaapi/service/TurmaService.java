package br.com.unisul.escolaapi.service;

import br.com.unisul.escolaapi.dto.TurmaDTO;
import br.com.unisul.escolaapi.entity.Turma;
import br.com.unisul.escolaapi.repository.TurmaRepository;
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
        String filtroParaListagem = "%" + filtro + "%";
        List<Turma> turmasEncontradas = turmaRepository.listarPor(filtroParaListagem);
        return turmasEncontradas.stream().map(TurmaDTO::new).toList();
    }

    public TurmaDTO buscarPor(Long id) {
        Turma turma = turmaRepository.buscarPor(id);
        TurmaDTO turmaDTO = new TurmaDTO(turma);
        turmaDTO.setAlunos(alunoService.listarPor(turma));
        turmaDTO.setProfessores(professorService.listarPor(turma));
        return turmaDTO;
    }

    public TurmaDTO inserir(TurmaDTO turmaDTO) {
        Turma turma = turmaRepository.save(new Turma(turmaDTO));
        return new TurmaDTO(turma);
    }

    public TurmaDTO alterar(TurmaDTO turmaDTO) {
        Optional.ofNullable(turmaRepository.buscarPor(turmaDTO.getId()))
                .orElseThrow(() -> new IllegalArgumentException("A turma '" + turmaDTO.getId() + "' não existe"));
        Turma turma = new Turma(turmaDTO);
        turma.setDataDeCriacao(turmaDTO.getDataDeCriacao());
        Turma turmaSalva = turmaRepository.save(turma);
        return new TurmaDTO(turmaSalva);
    }

}
