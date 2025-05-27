package br.com.unisul.escolaapi.repository;

import br.com.unisul.escolaapi.entity.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {

    @Query(value = "SELECT p " +
            "FROM Professor p " +
            "JOIN FETCH p.turmas " +
            "WHERE p.id = :id")
    Professor buscarPor(@Param("id") Long id);

    @Query(value = "SELECT p " +
            "FROM Professor p " +
            "JOIN FETCH p.turmas " +
            "WHERE Upper(p.nomeCompleto) LIKE Upper(:nome)")
    List<Professor> listarPor(@Param("nome") String nome);

}
