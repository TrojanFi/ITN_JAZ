package pl.edu.pjwstk.jaz.DataBase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import pl.edu.pjwstk.jaz.dao.UserRepository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;
import javax.transaction.Transactional;

@Repository
@Transactional
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository, EntityManager entityManager) {
        this.userRepository = userRepository;
        this.entityManager = entityManager;
    }

    private final EntityManager entityManager;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void saveUser(String username, String password) {
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
    public UserEntity findUserByUsername(String username) {
            return entityManager.createQuery("select ue from UserEntity ue where ue.username = :username", UserEntity.class)
                    .setParameter("username", username)//username
                    .getSingleResult();
    }

    //liczba userów
    public Number users() {
        Query q = entityManager.createQuery("SELECT count(ue) FROM UserEntity ue");
        Number result = (Number) q.getSingleResult();
        return result;
    }

    //czy jest user
    public boolean userExist(String username) {
        UserEntity name = userRepository.findByUsername(username).orElseGet(UserEntity::new);
        if (name.getUsername() == null)
            return false;
        return true;
    }
}
