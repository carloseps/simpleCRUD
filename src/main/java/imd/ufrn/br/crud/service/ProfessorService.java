package imd.ufrn.br.crud.service;

import imd.ufrn.br.crud.dto.ProfessorDTO;
import imd.ufrn.br.crud.entity.ProfessorEntity;
import imd.ufrn.br.crud.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfessorService {

    @Autowired
    private ProfessorRepository professorRepository;

    public List<ProfessorEntity> getAllProfessores() {
        return (List<ProfessorEntity>) professorRepository.findAll();
    }

    public Optional<ProfessorEntity> getProfessorById(Long id) {
        return professorRepository.findById(id);
    }

    public ProfessorEntity saveProfessor(ProfessorDTO profDTO) {
        ProfessorEntity professor = new ProfessorEntity(
                profDTO.cpf(),
                profDTO.nome(),
                profDTO.matricula(),
                profDTO.genero(),
                profDTO.departamento(),
                profDTO.dataNascimento(),
                profDTO.salario(),
                profDTO.ativo(),
                profDTO.turmas()
        );
        return professorRepository.save(professor);
    }

    public ProfessorEntity updateProfessor(Long id, ProfessorEntity professorDetails) {
        return professorRepository.findById(id).map(professor -> {
            professor.setNome(professorDetails.getNome());
            professor.setCpf(professorDetails.getCpf());
            professor.setMatricula(professorDetails.getMatricula());
            professor.setGenero(professorDetails.getGenero());
            professor.setDepartamento(professorDetails.getDepartamento());
            professor.setDataNascimento(professorDetails.getDataNascimento());
            professor.setSalario(professorDetails.getSalario());
            professor.setAtivo(professorDetails.isAtivo());
            professor.setTurmas(professorDetails.getTurmas());
            return professorRepository.save(professor);
        }).orElse(null);
    }

    public boolean deleteProfessor(Long id) {
        if (professorRepository.existsById(id)) {
            professorRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public boolean deleteLogicProfessor(Long id) {
        return professorRepository.findById(id).map(professor -> {
            professor.setAtivo(false);
            professorRepository.save(professor);
            return true;
        }).orElse(false);
    }
}
