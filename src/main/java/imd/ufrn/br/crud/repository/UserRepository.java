package imd.ufrn.br.crud.repository;

import imd.ufrn.br.crud.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserDetails findByLogin(String login);
}
