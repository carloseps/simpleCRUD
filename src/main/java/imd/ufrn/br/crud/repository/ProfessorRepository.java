package imd.ufrn.br.crud.repository;

import imd.ufrn.br.crud.entity.ProfessorEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessorRepository extends CrudRepository<ProfessorEntity, Long> {

}
