package br.com.unisul.escolaapi.repository;

import br.com.unisul.escolaapi.entity.Turma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TurmaRepository extends JpaRepository<Turma, Long> {

    @Query(value = "SELECT t " +
            "FROM Turma t " +
            "JOIN FETCH t.professores " +
            "JOIN FETCH t.alunos " +
            "WHERE upper(t.nome) LIKE upper(:filtro)")
    List<Turma> listarPor(@Param("filtro") String filtro);


    @Query(value = "SELECT t " +
            "FROM Turma t " +
            "WHERE t.id = :id")
    Turma buscarPor(@Param("id") Long id);

}
