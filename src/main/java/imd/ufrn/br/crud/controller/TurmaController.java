package imd.ufrn.br.crud.controller;

import imd.ufrn.br.crud.dto.TurmaDTO;
import imd.ufrn.br.crud.entity.TurmaEntity;
import imd.ufrn.br.crud.service.TurmaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/turmas")
public class TurmaController {

    @Autowired
    private TurmaService turmaService;

    @GetMapping("/getall")
    public ResponseEntity<List<TurmaEntity>> getAllTurmas() {
        return ResponseEntity.ok(turmaService.getAllTurmas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TurmaEntity> getTurmaById(@PathVariable Long id) {
        return turmaService.getTurmaById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<TurmaEntity> createTurma(@Valid @RequestBody TurmaDTO turmaDTO) {
        TurmaEntity createdTurma = turmaService.saveTurma(turmaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTurma);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TurmaEntity> updateTurma(@PathVariable Long id, @Valid @RequestBody TurmaEntity turmaDetails) {
        return turmaService.updateTurma(id, turmaDetails) != null
                ? ResponseEntity.ok(turmaService.updateTurma(id, turmaDetails))
                : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTurma(@PathVariable Long id) {
        return turmaService.deleteTurma(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

    @PatchMapping("/deleteLogic/{id}")
    public ResponseEntity<Void> deleteLogicTurma(@PathVariable Long id) {
        return turmaService.deleteLogicTurma(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

    @PostMapping("/{turmaId}/alunos/{alunoId}")
    public ResponseEntity<TurmaEntity> matricularAluno(@PathVariable Long turmaId, @PathVariable Long alunoId) {
        return turmaService.matricularAluno(turmaId, alunoId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{turmaId}/alunos/{alunoId}")
    public ResponseEntity<TurmaEntity> removerAluno(@PathVariable Long turmaId, @PathVariable Long alunoId) {
        return turmaService.removerAluno(turmaId, alunoId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/{turmaId}/professores/{professorId}")
    public ResponseEntity<TurmaEntity> associarProfessor(@PathVariable Long turmaId, @PathVariable Long professorId) {
        return turmaService.associarProfessor(turmaId, professorId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{turmaId}/professores/{professorId}")
    public ResponseEntity<TurmaEntity> removerProfessor(@PathVariable Long turmaId, @PathVariable Long professorId) {
        return turmaService.removerProfessor(turmaId, professorId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
