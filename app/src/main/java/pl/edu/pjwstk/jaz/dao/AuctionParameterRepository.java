package pl.edu.pjwstk.jaz.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pjwstk.jaz.DataBase.AuctionParameterEntity;

@Repository
public interface AuctionParameterRepository extends JpaRepository<AuctionParameterEntity,Long> {
}
