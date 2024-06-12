package imd.ufrn.br.crud.service;

import imd.ufrn.br.crud.dto.AlunoDTO;
import imd.ufrn.br.crud.entity.AlunoEntity;
import imd.ufrn.br.crud.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    public List<AlunoEntity> getAllAlunos() {
        return (List<AlunoEntity>) alunoRepository.findAll();
    }

    public Optional<AlunoEntity> getAlunoById(Long id) {
        return alunoRepository.findById(id);
    }

    public AlunoEntity saveAluno(AlunoDTO alunoDTO) {
        AlunoEntity aluno = new AlunoEntity(
                alunoDTO.nome(),
                alunoDTO.cpf(),
                alunoDTO.matricula(),
                alunoDTO.genero(),
                alunoDTO.curso(),
                alunoDTO.dataNascimento(),
                alunoDTO.ativo(),
                alunoDTO.turmas()
        );
        return alunoRepository.save(aluno);
    }

    public AlunoEntity updateAluno(Long id, AlunoEntity alunoDetails) {
        return alunoRepository.findById(id).map(aluno -> {
            aluno.setNome(alunoDetails.getNome());
            aluno.setCpf(alunoDetails.getCpf());
            aluno.setMatricula(alunoDetails.getMatricula());
            aluno.setGenero(alunoDetails.getGenero());
            aluno.setCurso(alunoDetails.getCurso());
            aluno.setDataNascimento(alunoDetails.getDataNascimento());
            aluno.setAtivo(alunoDetails.isAtivo());
            aluno.setTurmas(alunoDetails.getTurmas());
            return alunoRepository.save(aluno);
        }).orElse(null);
    }

    public boolean deleteAluno(Long id) {
        if (alunoRepository.existsById(id)) {
            alunoRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public boolean deleteLogicAluno(Long id) {
        return alunoRepository.findById(id).map(aluno -> {
            aluno.setAtivo(false);
            alunoRepository.save(aluno);
            return true;
        }).orElse(false);
    }
}
