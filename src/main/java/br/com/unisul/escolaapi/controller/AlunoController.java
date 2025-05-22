package br.com.unisul.escolaapi.controller;

import br.com.unisul.escolaapi.service.AlunoService;
import br.com.unisul.escolaapi.dto.AlunoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    public AlunoService service;

    @PostMapping
    public ResponseEntity<AlunoDTO> inserir(AlunoDTO aluno) {
        AlunoDTO novoAluno = service.inserir(aluno);
        return ResponseEntity.created(URI.create("/alunos/" + novoAluno.getId())).build();
    }

    @PutMapping
    public ResponseEntity<AlunoDTO> alterar(AlunoDTO aluno) {
        AlunoDTO alunoSalvo = service.alterar(aluno);
        return ResponseEntity.ok(alunoSalvo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlunoDTO> buscarAlunoPor(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.buscarPor(id));
    }

    @GetMapping("/{matricula}")
    public ResponseEntity<AlunoDTO> buscarAlunoPor(@PathVariable("matricula") String matricula) {
        return ResponseEntity.ok(service.buscarPor(matricula));
    }

    @GetMapping
    public ResponseEntity<List<AlunoDTO>> getAlunos(@RequestParam("filtro") String filtro) {
        return ResponseEntity.ok(service.listarPor(filtro));
    }

}
