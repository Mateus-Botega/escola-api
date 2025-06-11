package br.com.unisul.escolaapi.service;

import br.com.unisul.escolaapi.dto.AlunoDTO;
import br.com.unisul.escolaapi.entity.Aluno;
import br.com.unisul.escolaapi.entity.Turma;
import br.com.unisul.escolaapi.repository.AlunoRepository;
import jakarta.persistence.NoResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository repository;

    public AlunoDTO inserir(AlunoDTO aluno) {
        validar(aluno);
        return new AlunoDTO(repository.save(new Aluno(aluno)));
    }

    public AlunoDTO alterar(AlunoDTO aluno) {
        Aluno alunoExistente = repository.buscarPor(aluno.getId());
        if (alunoExistente == null) {
            throw new IllegalArgumentException("O aluno '" + aluno.getId() + "' não existe.");
        }
        validar(aluno);
        alunoExistente.setNomeCompleto(aluno.getNomeCompleto());
        alunoExistente.setMatricula(aluno.getMatricula());
        alunoExistente.setDataDeNascimento(aluno.getDataDeNascimento());
        alunoExistente.setTurma(new Turma(aluno.getTurma()));
        return new AlunoDTO(repository.saveAndFlush(alunoExistente));
    }

    public AlunoDTO buscarPor(Long id) {
        Aluno alunoSalvo = Optional.ofNullable(repository.buscarPor(id))
                .orElseThrow(() -> new NoResultException("O aluno '" + id + "' não existe."));
        AlunoDTO dto = new AlunoDTO(alunoSalvo);
        dto.getTurma().setAlunos(null);
        return dto;
    }

    public AlunoDTO buscarPor(String matricula) {
        Aluno alunoSalvo = Optional.ofNullable(repository.buscarPor(matricula))
                .orElseThrow(() -> new NoResultException("A matrícula '" + matricula + "' não existe."));
        alunoSalvo.getTurma().setAlunos(null);
        return new AlunoDTO(alunoSalvo);
    }

    public List<AlunoDTO> listarPor(String filtro) {
        List<Aluno> alunos = repository.listarPor("%" + filtro + "%");
        return alunos.stream().map(AlunoDTO::new).toList();
    }

    public List<AlunoDTO> listarPor(Turma turma) {
        List<Aluno> alunos = repository.listarPor(turma);
        return alunos.stream().map(AlunoDTO::new).toList();
    }

    private void validar(AlunoDTO aluno) {
        Aluno alunoSalvo = repository.buscarPor(aluno.getMatricula());

        if (alunoSalvo != null && !alunoSalvo.getId().equals(aluno.getId())) {
            throw new IllegalArgumentException("A matrícula não deve ser repetida");
        }
    }

}
