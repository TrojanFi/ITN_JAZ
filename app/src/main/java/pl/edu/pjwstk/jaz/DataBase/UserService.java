package pl.edu.pjwstk.jaz.DataBase;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class UserService {
    private final EntityManager entityManager;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void saveUser(String username) {
        var userEntity = new UserEntity();

        userEntity.setUsername(username);
        userEntity.setId((long) 0);
        userEntity.setPassword("admin");

        //zapisanie użytkownika do bazy danych
        entityManager.persist(userEntity);
    }
    //zwracanie user
    public UserEntity findUserByUsername(String username){
      return entityManager.createQuery("select ue from UserEntity ue where ue.id = :id", UserEntity.class)
        .setParameter("id",0)//username
        .getSingleResult();
    }
}
