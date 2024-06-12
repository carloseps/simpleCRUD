package imd.ufrn.br.crud.controller;

import imd.ufrn.br.crud.dto.ProfessorDTO;
import imd.ufrn.br.crud.entity.ProfessorEntity;
import imd.ufrn.br.crud.service.ProfessorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/professores")
public class ProfessorController {

    @Autowired
    private ProfessorService professorService;

    @GetMapping("/getall")
    public ResponseEntity<List<ProfessorEntity>> getAllProfessores() {
        return ResponseEntity.ok(professorService.getAllProfessores());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfessorEntity> getProfessorById(@PathVariable Long id) {
        return professorService.getProfessorById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<ProfessorEntity> createProfessor(@Valid @RequestBody ProfessorDTO professorDTO) {
        ProfessorEntity createdProfessor = professorService.saveProfessor(professorDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProfessor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProfessorEntity> updateProfessor(@PathVariable Long id, @Valid @RequestBody ProfessorEntity professorDetails) {
        return professorService.updateProfessor(id, professorDetails) != null
                ? ResponseEntity.ok(professorService.updateProfessor(id, professorDetails))
                : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProfessor(@PathVariable Long id) {
        return professorService.deleteProfessor(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

    @PatchMapping("/deleteLogic/{id}")
    public ResponseEntity<Void> deleteLogicProfessor(@PathVariable Long id) {
        return professorService.deleteLogicProfessor(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
