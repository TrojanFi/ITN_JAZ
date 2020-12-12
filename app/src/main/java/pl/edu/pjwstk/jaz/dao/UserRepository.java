package pl.edu.pjwstk.jaz.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.pjwstk.jaz.DataBase.UserEntity;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity,Long> {
    public Optional<UserEntity> findByUsername(String username);
}
