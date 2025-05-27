package br.com.unisul.escolaapi.service;

import br.com.unisul.escolaapi.dto.TurmaDTO;
import br.com.unisul.escolaapi.entity.Turma;
import br.com.unisul.escolaapi.repository.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TurmaService {

    @Autowired
    private TurmaRepository turmaRepository;

    public List<TurmaDTO> listarPor(String filtro) {
        String filtroParaListagem = "%" + filtro + "%";
        List<Turma> turmasEncontradas = turmaRepository.listarPor(filtroParaListagem);
        return turmasEncontradas.stream().map(TurmaDTO::new).toList();
    }

    public TurmaDTO buscarPor(Long id) {
        Turma turma = turmaRepository.buscarPor(id);
        return new TurmaDTO(turma);
    }

    public TurmaDTO inserir(TurmaDTO turmaDTO) {
        Turma turma = turmaRepository.save(new Turma(turmaDTO));
        return new TurmaDTO(turma);
    }

    public TurmaDTO alterar(TurmaDTO turmaDTO) {
        Turma turma = turmaRepository.save(new Turma(turmaDTO));
        return new TurmaDTO(turma);
    }

}
