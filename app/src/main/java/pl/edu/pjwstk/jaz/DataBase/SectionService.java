package pl.edu.pjwstk.jaz.DataBase;

import org.springframework.stereotype.Service;
import pl.edu.pjwstk.jaz.dao.*;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class SectionService {

    private final SectionRepository sectionRepository;
    private final CategoryRepository categoryRepository;
    private final AuctionRepository auctionRepository;
    private final ParameterRepository parameterRepository;
    private final EntityManager entityManager;

    public SectionService(SectionRepository sectionRepository, CategoryRepository categoryRepository, AuctionRepository auctionRepository, ParameterRepository parameterRepository, EntityManager entityManager) {
        this.sectionRepository = sectionRepository;
        this.categoryRepository = categoryRepository;
        this.auctionRepository = auctionRepository;
        this.parameterRepository = parameterRepository;
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

    public void addAuction(String categoryName, String title, String description,int price,Long owner_id,List<String> photos,List<String> values,List<String> parameters) {
        CategoryEntity categoryEntity = categoryRepository.findByName(categoryName).orElseGet(CategoryEntity::new);
        if(categoryEntity.getName() == null){
            System.out.println("No section like that");
        }
        else {
            AuctionEntity auctionEntity = new AuctionEntity(title,description,owner_id,price);
            categoryEntity.getAuctions().add(auctionEntity);
            Long position = (long) 1;
            for (String photo : photos) {
                PhotoEntity photoEntity = new PhotoEntity(photo, position++);
                auctionEntity.getPhotos().add(photoEntity);
            }

            for (int i = 0; i < parameters.size(); i++) {
            ParameterEntity parameterEntity = parameterRepository.findByName(parameters.get(i)).orElseGet(ParameterEntity::new);
            if(parameterEntity.getName() == null){
                parameterEntity.setName(parameters.get(i));
                this.parameterRepository.save(parameterEntity);
            }
            else {
                System.out.println("Parameter already exists ");
            }

            this.auctionRepository.save(auctionEntity);
            this.parameterRepository.save(parameterEntity);
            AuctionParameterEntity auctionParameterEntity = new AuctionParameterEntity(new AuctionParameterKey(auctionEntity.getId(),parameterEntity.getId()),values.get(i));
            auctionParameterEntity.setAuctionEntity(auctionEntity);
            auctionParameterEntity.setParameterEntity(parameterEntity);
            auctionEntity.addAuctionParameter(auctionParameterEntity);
            entityManager.merge(auctionParameterEntity);
            }





//           for (String value : values) {


//           }
//            for (String parameter : parameters) {
//                ParameterEntity parameterEntity = new ParameterEntity(parameter);
//                System.out.println(parameterEntity);
//            }
        }
    }

}
