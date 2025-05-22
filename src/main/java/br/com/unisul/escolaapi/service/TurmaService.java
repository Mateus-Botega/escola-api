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

    public List<Turma> listarPor(String filtro) {
        String filtroParaListagem = "%" + filtro + "%";
        return turmaRepository.listarPor(filtroParaListagem);
    }

    public Turma buscarPor(Long id) {
        return turmaRepository.buscarPor(id);
    }

    public void salvar(TurmaDTO turmaDTO) {
        Turma turma = converterParaEntidade(turmaDTO);
        turmaRepository.save(turma);
    }

    private Turma converterParaEntidade(TurmaDTO turmaDTO) {
        return new Turma(turmaDTO);
    }

}
