package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Sponsor;
import lt.vu.entities.Team;
import lt.vu.persistence.SponsorDAO;
import lt.vu.persistence.TeamsDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class Sponsors {

    @Inject
    private SponsorDAO sponsorDAO;

    @Getter @Setter
    private Sponsor sponsorToCreate = new Sponsor();

    @Getter
    private List<Sponsor> allSponsors;

    @PostConstruct
    public void init(){
        loadAllSponsors();
    }

    @Transactional
    public void createSponsor(){
        this.sponsorDAO.persist(sponsorToCreate);
    }

    private void loadAllSponsors(){
        this.allSponsors = sponsorDAO.loadAll();
    }
}
