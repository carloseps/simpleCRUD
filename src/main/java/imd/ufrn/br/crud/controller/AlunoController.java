package imd.ufrn.br.crud.controller;

import imd.ufrn.br.crud.dto.AlunoDTO;
import imd.ufrn.br.crud.entity.AlunoEntity;
import imd.ufrn.br.crud.repository.AlunoRepository;
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
    private AlunoRepository alunoRepository;

    @GetMapping("/getall")
    public ResponseEntity<List<AlunoEntity>> getAllAlunos() {
        List<AlunoEntity> alunos = (List<AlunoEntity>) alunoRepository.findAll();
        return ResponseEntity.ok(alunos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlunoEntity> getAlunoById(@PathVariable Long id) {
        AlunoEntity aluno = alunoRepository.findById(id).orElse(null);
        if (aluno != null) {
            return ResponseEntity.ok(aluno);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/add")
    public ResponseEntity<AlunoEntity> postAluno(@Valid @RequestBody AlunoDTO alunoDTO) {
        AlunoEntity aluno = new AlunoEntity(
                alunoDTO.nome(),
                alunoDTO.cpf(),
                alunoDTO.matricula(),
                alunoDTO.genero(),
                alunoDTO.curso(),
                alunoDTO.dataNascimento(),
                alunoDTO.ativo()
        );
        AlunoEntity savedAluno = alunoRepository.save(aluno);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAluno);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlunoEntity> putAluno(@PathVariable Long id, @RequestBody AlunoEntity alunoDetails) {
        AlunoEntity aluno = alunoRepository.findById(id).orElse(null);
        if (aluno != null) {
            aluno.setNome(alunoDetails.getNome());
            aluno.setCpf(alunoDetails.getCpf());
            aluno.setMatricula(alunoDetails.getMatricula());
            aluno.setGenero(alunoDetails.getGenero());
            aluno.setCurso(alunoDetails.getCurso());
            aluno.setDataNascimento(alunoDetails.getDataNascimento());
            return ResponseEntity.ok(alunoRepository.save(aluno));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAluno(@PathVariable Long id) {
        if (alunoRepository.existsById(id)) {
            alunoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> deleteLogicAluno(@PathVariable Long id) {
        AlunoEntity aluno = alunoRepository.findById(id).orElse(null);
        if (aluno != null) {
            aluno.setAtivo(false);
            alunoRepository.save(aluno);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
