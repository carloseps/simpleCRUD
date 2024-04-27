package imd.ufrn.br.crud.repository;

import imd.ufrn.br.crud.entity.AlunoEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlunoRepository extends CrudRepository<AlunoEntity, Long> {

}
