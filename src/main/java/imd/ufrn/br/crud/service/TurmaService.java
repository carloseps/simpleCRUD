package imd.ufrn.br.crud.service;

import imd.ufrn.br.crud.dto.TurmaDTO;
import imd.ufrn.br.crud.entity.AlunoEntity;
import imd.ufrn.br.crud.entity.ProfessorEntity;
import imd.ufrn.br.crud.entity.TurmaEntity;
import imd.ufrn.br.crud.repository.AlunoRepository;
import imd.ufrn.br.crud.repository.ProfessorRepository;
import imd.ufrn.br.crud.repository.TurmaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TurmaService {

    @Autowired
    private TurmaRepository turmaRepository;

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private ProfessorRepository professorRepository;

    public List<TurmaEntity> getAllTurmas() {
        return (List<TurmaEntity>) turmaRepository.findAll();
    }

    public Optional<TurmaEntity> getTurmaById(Long id) {
        return turmaRepository.findById(id);
    }

    public TurmaEntity saveTurma(TurmaDTO turmaDTO) {
        TurmaEntity turma = new TurmaEntity(
                turmaDTO.nome(),
                turmaDTO.codigo(),
                turmaDTO.alunos(),
                turmaDTO.professores(),
                turmaDTO.ativa()
        );
        return turmaRepository.save(turma);
    }

    public TurmaEntity updateTurma(Long id, TurmaEntity turmaDetails) {
        return turmaRepository.findById(id).map(turma -> {
            turma.setNome(turmaDetails.getNome());
            turma.setCodigo(turmaDetails.getCodigo());
            turma.setAtiva(turmaDetails.isAtiva());
            turma.setAlunos(turmaDetails.getAlunos());
            turma.setProfessores(turmaDetails.getProfessores());
            return turmaRepository.save(turma);
        }).orElse(null);
    }

    public boolean deleteTurma(Long id) {
        if (turmaRepository.existsById(id)) {
            turmaRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public boolean deleteLogicTurma(Long id) {
        return turmaRepository.findById(id).map(turma -> {
            turma.setAtiva(false);
            turmaRepository.save(turma);
            return true;
        }).orElse(false);
    }

    public Optional<TurmaEntity> matricularAluno(Long turmaId, Long alunoId) {
        return turmaRepository.findById(turmaId).map(turma -> {
            AlunoEntity aluno = alunoRepository.findById(alunoId)
                    .orElseThrow(() -> new EntityNotFoundException("Aluno não encontrado com o ID fornecido"));
            turma.getAlunos().add(aluno);
            aluno.getTurmas().add(turma);
            turmaRepository.save(turma);
            alunoRepository.save(aluno);
            return turma;
        });
    }

    public Optional<TurmaEntity> removerAluno(Long turmaId, Long alunoId) {
        return turmaRepository.findById(turmaId).map(turma -> {
            AlunoEntity aluno = alunoRepository.findById(alunoId)
                    .orElseThrow(() -> new EntityNotFoundException("Aluno não encontrado com o ID fornecido"));
            turma.getAlunos().remove(aluno);
            aluno.getTurmas().remove(turma);
            turmaRepository.save(turma);
            alunoRepository.save(aluno);
            return turma;
        });
    }

    public Optional<TurmaEntity> associarProfessor(Long turmaId, Long professorId) {
        return turmaRepository.findById(turmaId).map(turma -> {
            ProfessorEntity professor = professorRepository.findById(professorId)
                    .orElseThrow(() -> new EntityNotFoundException("Professor não encontrado com o ID fornecido"));
            turma.getProfessores().add(professor);
            professor.getTurmas().add(turma);
            turmaRepository.save(turma);
            professorRepository.save(professor);
            return turma;
        });
    }

    public Optional<TurmaEntity> removerProfessor(Long turmaId, Long professorId) {
        return turmaRepository.findById(turmaId).map(turma -> {
            ProfessorEntity professor = professorRepository.findById(professorId)
                    .orElseThrow(() -> new EntityNotFoundException("Professor não encontrado com o ID fornecido"));
            turma.getProfessores().remove(professor);
            professor.getTurmas().remove(turma);
            turmaRepository.save(turma);
            professorRepository.save(professor);
            return turma;
        });
    }
}
