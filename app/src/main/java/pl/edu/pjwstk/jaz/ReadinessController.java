package pl.edu.pjwstk.jaz;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;


@RestController
public class ReadinessController {

    private final EntityManager entityManager;

    public ReadinessController(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    @GetMapping("auth0/is-ready")
    public void readinessTest() {
        var entity = new Test1Entity();
        entity.setName("sdavsda");
        entityManager.persist(entity);
    }
}
