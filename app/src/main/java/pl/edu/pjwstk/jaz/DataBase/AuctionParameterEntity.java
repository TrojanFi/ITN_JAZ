package pl.edu.pjwstk.jaz.DataBase;

import javax.persistence.*;

@Entity
@Table(name = "auction_parameter")
public class AuctionParameterEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long auction_id;


}
