package br.com.unisul.escolaapi.controller;

import br.com.unisul.escolaapi.dto.ProfessorDTO;
import br.com.unisul.escolaapi.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.naming.directory.SearchResult;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/professores")
public class ProfessorController {

    @Autowired
    private ProfessorService service;

    @PostMapping
    public ResponseEntity<ProfessorDTO> inserir(ProfessorDTO professorDTO) {
        ProfessorDTO novoProfessor = service.inserir(professorDTO);
        return ResponseEntity.created(URI.create("/professores/" + novoProfessor.getId())).build();
    }

    @PutMapping
    public ResponseEntity<ProfessorDTO> alterar(ProfessorDTO professorDTO) {
        ProfessorDTO professorSalvo = service.alterar(professorDTO);
        return ResponseEntity.ok(professorSalvo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfessorDTO> buscarPor(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.buscarPor(id));
    }

    @GetMapping
    public ResponseEntity<List<ProfessorDTO>> listarPor(String nome) {
        return ResponseEntity.ok(service.listarPor(nome));
    }

}
