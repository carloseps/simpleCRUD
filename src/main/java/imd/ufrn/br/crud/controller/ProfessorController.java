package imd.ufrn.br.crud.controller;

import imd.ufrn.br.crud.dto.ProfessorDTO;
import imd.ufrn.br.crud.entity.ProfessorEntity;
import imd.ufrn.br.crud.repository.ProfessorRepository;
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
    private ProfessorRepository professorRepository;

    @GetMapping("/getall")
    public ResponseEntity<List<ProfessorEntity>> getAllProfessores() {
        List<ProfessorEntity> professores = (List<ProfessorEntity>) professorRepository.findAll();
        return ResponseEntity.ok(professores);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfessorEntity> getProfessorById(@PathVariable Long id) {
        ProfessorEntity professor = professorRepository.findById(id).orElse(null);
        if (professor != null) {
            return ResponseEntity.ok(professor);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/add")
    public ResponseEntity<ProfessorEntity> postProfessor(@Valid @RequestBody ProfessorDTO profDTO) {
        ProfessorEntity professor = new ProfessorEntity(
                profDTO.cpf(),
                profDTO.nome(),
                profDTO.matricula(),
                profDTO.genero(),
                profDTO.departamento(),
                profDTO.dataNascimento(),
                profDTO.salario(),
                profDTO.disciplinaAssociada(),
                profDTO.ativo()
        );
        ProfessorEntity savedProfessor = professorRepository.save(professor);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProfessor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProfessorEntity> putProfessor(@PathVariable Long id, @RequestBody ProfessorEntity professorDetails) {
        ProfessorEntity professor = professorRepository.findById(id).orElse(null);
        if (professor != null) {
            professor.setNome(professorDetails.getNome());
            professor.setCpf(professorDetails.getCpf());
            professor.setMatricula(professorDetails.getMatricula());
            professor.setGenero(professorDetails.getGenero());
            professor.setDepartamento(professorDetails.getDepartamento());
            professor.setDataNascimento(professorDetails.getDataNascimento());
            professor.setSalario(professorDetails.getSalario());
            professor.setDisciplinaAssociada(professorDetails.getDisciplinaAssociada());
            return ResponseEntity.ok(professorRepository.save(professor));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProfessor(@PathVariable Long id) {
        if (professorRepository.existsById(id)) {
            professorRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> deleteLogicProfessor(@PathVariable Long id) {
        ProfessorEntity professor = professorRepository.findById(id).orElse(null);
        if (professor != null) {
            professor.setAtivo(false);
            professorRepository.save(professor);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
