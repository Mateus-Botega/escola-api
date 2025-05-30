package br.com.unisul.escolaapi.repository;

import br.com.unisul.escolaapi.entity.Aluno;
import br.com.unisul.escolaapi.entity.Turma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Integer> {

    @Query("SELECT a " +
            "FROM Aluno a " +
            "JOIN FETCH a.turma " +
            "WHERE a.id = :id")
    Aluno buscarPor(Long id);

    @Query("SELECT a " +
            "FROM Aluno a " +
            "JOIN FETCH a.turma " +
            "WHERE a.matricula LIKE :matricula")
    Aluno buscarPor(String matricula);

    @Query("SELECT a " +
            "FROM Aluno a " +
            "JOIN FETCH a.turma " +
            "WHERE upper(a.nomeCompleto) LIKE upper(:filtro)" +
            "OR upper(a.matricula) LIKE upper(:filtro)")
    List<Aluno> listarPor(String filtro);

    @Query("SELECT a " +
            "FROM Aluno a " +
            "WHERE a.turma = :turma")
    List<Aluno> listarPor(Turma turma);

}
