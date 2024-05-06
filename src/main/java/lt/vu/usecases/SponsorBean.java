package lt.vu.usecases;


import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Sponsor;
import lt.vu.entities.Team;
//import lt.vu.mybatis.dao.SponsorMapper;
import lt.vu.persistence.SponsorDAO;
import lt.vu.persistence.TeamsDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

import javax.transaction.Transactional;

@Named
@RequestScoped
public class SponsorBean {

    @Inject
    private TeamsForSponsors sponsorService;
//    @Inject
//    private SponsorMapper sponsorMapper;
//
//    @Inject
//    private PlayerMapper playerMapper;

    @Inject
    private SponsorDAO sponsorDAO;
    @Inject
    private TeamsDAO teamsDAO;

    @Getter
    @Setter
    private Sponsor newSponsor = new Sponsor();

    @Getter
    @Setter
    private Integer selectedTeamId;
    @Getter @Setter
    private Integer selectedSponsorId;

    @Getter @Setter
    private List<Team> availableTeams;
    @Getter @Setter
    private List<Sponsor> allSponsors;

    @PostConstruct
    public void init() {
        refreshSponsors(); // Pre-load tournaments on bean initialization
        availableTeams = teamsDAO.loadAll();
    }

    private void refreshSponsors() {
        allSponsors = sponsorDAO.loadAll();
    }

    @Transactional
    public String createSponsor() {
        try {
            sponsorDAO.persist(newSponsor);
            refreshSponsors();
            return "index?faces-redirect=true";
        } catch (Exception e) {
            // Log the error or handle it appropriately
            return "index";
        }

    }


//
//    public List<Sponsor> loadSponsors() {
//        if (sponsors == null) {
//            sponsors = sponsorMapper.selectAll();
//        }
//
//        for (Sponsor sponsor : sponsors) {
//            System.out.println("Sponsor: " + sponsor.getName());
//            for (Team team : sponsor.getTeams()) {
//                System.out.println("Team: " + team.getName());
//            }
//        }
//
//
//        return sponsors;
//    }


    public String addTeamToSponsor() {
        sponsorService.addTeamToSponsor(selectedTeamId, selectedSponsorId);
        refreshSponsors();
        return "sponsorDetails?faces-redirect=true"; // Redirect to the tournament list page
    }

    // Other methods and logic as necessary


}
