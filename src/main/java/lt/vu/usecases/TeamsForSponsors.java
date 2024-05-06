package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Player;
import lt.vu.entities.Sponsor;
import lt.vu.entities.Team;
import lt.vu.interceptors.LoggedInvocation;
import lt.vu.persistence.PlayersDAO;
import lt.vu.persistence.SponsorDAO;
import lt.vu.persistence.TeamsDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Model
public class TeamsForSponsors implements Serializable {

    @Inject
    private SponsorDAO sponsorDAO;

    @Inject
    private TeamsDAO teamsDAO;

    @Getter @Setter
    private Team team;

    @Transactional
    public void addTeamToSponsor(Integer teamId, Integer sponsorId) {
        System.out.println("Attempting to add team to sponsor");
        Team team = teamsDAO.findOne(teamId);
        Sponsor sponsor = sponsorDAO.findOne(sponsorId);
        if (team != null && sponsor != null) {
            System.out.println("Found team and sponsor");
//            team.setSponsor(sponsor); // Since Team owns the relationship
            team.getSponsors().add(sponsor); // Since Player owns the relationship
            teamsDAO.update(team); // Persist changes
            System.out.println("Team added to sponsor");
        } else {
            System.out.println("Team or sponsor not found");
        }
    }


    public List<Team> getTeamsForSponsor(Integer sponsorId) {
        return sponsorDAO.findOne(sponsorId).getTeams();
    }
}
