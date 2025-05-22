package br.com.unisul.escolaapi.controller;

import br.com.unisul.escolaapi.dto.TurmaDTO;
import br.com.unisul.escolaapi.entity.Turma;
import br.com.unisul.escolaapi.service.TurmaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/turma")
public class TurmaController {

    @Autowired
    private TurmaService turmaService;

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPor(@PathVariable("id") Long id) {
        Turma turma = turmaService.buscarPor(id);
        return ResponseEntity.ok(turma);
    }

    @GetMapping
    public ResponseEntity<?> listarPor(@RequestParam("filtro") String filtro) {
        List<Turma> turmas = turmaService.listarPor(filtro);
        return ResponseEntity.ok(turmas);
    }

    @PostMapping
    public ResponseEntity<?> salvar(TurmaDTO turmaDTO) {
        turmaService.salvar(turmaDTO);
        //buscar o salvo e retornar
        return ResponseEntity.created(URI.create(null)).build();
    }

}
