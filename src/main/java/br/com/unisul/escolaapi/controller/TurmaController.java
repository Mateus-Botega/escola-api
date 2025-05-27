package br.com.unisul.escolaapi.controller;

import br.com.unisul.escolaapi.dto.TurmaDTO;
import br.com.unisul.escolaapi.service.TurmaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/turmas")
public class TurmaController {

    @Autowired
    private TurmaService service;

    @GetMapping("/{id}")
    public ResponseEntity<TurmaDTO> buscarPor(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.buscarPor(id));
    }

    @GetMapping
    public ResponseEntity<List<TurmaDTO>> listarPor(@RequestParam("filtro") String filtro) {
        return ResponseEntity.ok(service.listarPor(filtro));
    }

    @PostMapping
    public ResponseEntity<TurmaDTO> inserir(TurmaDTO turmaDTO) {
        TurmaDTO novaTurma = service.inserir(turmaDTO);
        return ResponseEntity.created(URI.create("/turmas/" + novaTurma.getId())).build();
    }

    @PutMapping
    public ResponseEntity<TurmaDTO> alterar(TurmaDTO turmaDTO) {
        TurmaDTO turmaSalva = service.alterar(turmaDTO);
        return ResponseEntity.ok(turmaSalva);
    }

}
