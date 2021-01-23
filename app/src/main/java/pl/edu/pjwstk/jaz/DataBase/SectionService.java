package pl.edu.pjwstk.jaz.DataBase;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pl.edu.pjwstk.jaz.Authorization.AuthenticationService;
import pl.edu.pjwstk.jaz.dao.CategoryRepository;
import pl.edu.pjwstk.jaz.dao.SectionRepository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class SectionService {

    private final SectionRepository sectionRepository;
    private final CategoryRepository categoryRepository;
    private final EntityManager entityManager;

    public SectionService(SectionRepository sectionRepository, CategoryRepository categoryRepository, EntityManager entityManager) {
        this.sectionRepository = sectionRepository;
        this.categoryRepository = categoryRepository;
        this.entityManager = entityManager;
    }

    public void addSection(String sectionName) {
        SectionEntity sectionEntity = new SectionEntity(sectionName);
        this.sectionRepository.save(sectionEntity);
    }

    public void addCategory(String sectionName, List<String> categories) {
        SectionEntity sectionEntity = sectionRepository.findByName(sectionName).orElseGet(SectionEntity::new);
        if(sectionEntity.getName() == null){
            System.out.println("No section like that");
        }
        else {
            for (String auto : categories) {
                System.out.println("Added Section : " + auto);
            }

            for (String category : categories) {
                CategoryEntity categoryEntity = new CategoryEntity(category);
                sectionEntity.getCategories().add(categoryEntity);
            }
        }
    }
    public void editSection(String sectionName,String newSectionName){
        SectionEntity sectionEntity = sectionRepository.findByName(sectionName).orElseGet(SectionEntity::new);
        if(sectionEntity.getName() == null){
            System.out.println("No section name like that");
        }
        else {
            sectionEntity.setName(newSectionName);
            this.sectionRepository.save(sectionEntity);
        }
    }

    public void addAuction(String categoryName, String title, String description,int price,Long owner_id) {
        CategoryEntity categoryEntity = categoryRepository.findByName(categoryName).orElseGet(CategoryEntity::new);
        if(categoryEntity.getName() == null){
            System.out.println("No section like that");
        }
        else {
            AuctionEntity auctionEntity = new AuctionEntity(title,description,owner_id,price);
            categoryEntity.getAuctions().add(auctionEntity);
        }
    }

}
