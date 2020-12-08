package pl.edu.pjwstk.jaz.DataBase;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Repository
@Transactional
public class UserService {
    private final EntityManager entityManager;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void saveUser(String username,String password) {
        var userEntity = new UserEntity();

        userEntity.setUsername(username);
   //     userEntity.setId((long) 0);
        userEntity.setPassword(password);

        //zapisanie użytkownika do bazy danych
//        entityManager.getTransaction().begin();
        entityManager.persist(userEntity);
//        entityManager.getTransaction().commit();
    }
    //zwracanie user
    public UserEntity findUserByUsername(String username){
      return entityManager.createQuery("select ue from UserEntity ue where ue.username = :username", UserEntity.class)
        .setParameter("username",username)//username
        .getSingleResult();
    }
}
