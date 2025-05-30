package br.com.unisul.escolaapi.service;

import br.com.unisul.escolaapi.entity.Aluno;
import br.com.unisul.escolaapi.entity.Turma;
import br.com.unisul.escolaapi.repository.AlunoRepository;
import br.com.unisul.escolaapi.dto.AlunoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository repository;

    public AlunoDTO inserir(AlunoDTO aluno) {
        validar(aluno);
        Aluno alunoSalvo = repository.save(new Aluno(aluno));
        return new AlunoDTO(alunoSalvo);
    }

    public AlunoDTO alterar(AlunoDTO aluno) {
        validar(aluno);
        Aluno alunoAlterado = repository.save(new Aluno(aluno));
        return new AlunoDTO(alunoAlterado);
    }

    public AlunoDTO buscarPor(Long id) {
        Aluno alunoSalvo = repository.buscarPor(id);
        return new AlunoDTO(alunoSalvo);
    }

    public AlunoDTO buscarPor(String matricula) {
        Aluno alunoSalvo = repository.buscarPor(matricula);
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
        String matricula = aluno.getMatricula();
        Aluno alunoSalvo = repository.buscarPor(matricula);

        if (alunoSalvo != null && alunoSalvo.getId().equals(aluno.getId())) {
            throw new IllegalArgumentException("A matrícula não deve ser repetida");
        }
    }

}
