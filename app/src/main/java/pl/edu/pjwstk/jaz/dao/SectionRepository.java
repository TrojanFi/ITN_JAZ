package pl.edu.pjwstk.jaz.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pjwstk.jaz.DataBase.CategoryEntity;
import pl.edu.pjwstk.jaz.DataBase.RoleEntity;
import pl.edu.pjwstk.jaz.DataBase.SectionEntity;
import pl.edu.pjwstk.jaz.DataBase.UserEntity;

import java.util.Optional;

@Repository
public interface SectionRepository extends JpaRepository<SectionEntity,Long> {
    public Optional<SectionEntity> findByName(String name);
}
