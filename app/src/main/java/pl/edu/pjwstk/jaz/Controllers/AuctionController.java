package pl.edu.pjwstk.jaz.Controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pjwstk.jaz.DataBase.SectionService;
import pl.edu.pjwstk.jaz.DataBase.UserService;
import pl.edu.pjwstk.jaz.Requests.AuctionRequest;

@RestController
public class AuctionController {
    private final SectionService sectionService;
    private final UserService userService;

    public AuctionController(SectionService sectionService, UserService userService) {
        this.sectionService = sectionService;
        this.userService = userService;
    }

    @PreAuthorize("hasAnyAuthority('Admin')")
    @PostMapping("/addAuction")
    public void addNewAuction(@RequestBody AuctionRequest auctionRequest){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(auth.getPrincipal());
        Long owner_id = userService.getIdFromUser(String.valueOf(auth.getPrincipal()));
        sectionService.addAuction(auctionRequest.getCategory(),auctionRequest.getTitle(),auctionRequest.getDescription(),auctionRequest.getPrice(),owner_id,auctionRequest.getPhotos(),auctionRequest.getValues(),auctionRequest.getParameters());
    }
}
