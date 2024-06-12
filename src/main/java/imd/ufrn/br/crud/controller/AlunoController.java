package imd.ufrn.br.crud.controller;

import imd.ufrn.br.crud.dto.AlunoDTO;
import imd.ufrn.br.crud.entity.AlunoEntity;
import imd.ufrn.br.crud.service.AlunoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    @GetMapping("/getall")
    public ResponseEntity<List<AlunoEntity>> getAllAlunos() {
        return ResponseEntity.ok(alunoService.getAllAlunos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlunoEntity> getAlunoById(@PathVariable Long id) {
        return alunoService.getAlunoById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<AlunoEntity> createAluno(@Valid @RequestBody AlunoDTO alunoDTO) {
        AlunoEntity createdAluno = alunoService.saveAluno(alunoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAluno);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlunoEntity> updateAluno(@PathVariable Long id, @Valid @RequestBody AlunoEntity alunoDetails) {
        return alunoService.updateAluno(id, alunoDetails) != null
                ? ResponseEntity.ok(alunoService.updateAluno(id, alunoDetails))
                : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAluno(@PathVariable Long id) {
        return alunoService.deleteAluno(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

    @PatchMapping("/deleteLogic/{id}")
    public ResponseEntity<Void> deleteLogicAluno(@PathVariable Long id) {
        return alunoService.deleteLogicAluno(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
