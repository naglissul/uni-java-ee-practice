package lt.vu.persistence;

import lt.vu.entities.Sponsor;
import lt.vu.entities.Team;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class SponsorDAO {

    @Inject
    private EntityManager em;

    public List<Sponsor> loadAll() {
        return em.createNamedQuery("Sponsor.findAll", Sponsor.class).getResultList();
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public void persist(Sponsor sponsor){
        this.em.persist(sponsor);
    }

    public Sponsor findOne(Integer id) {
        return em.find(Sponsor.class, id);
    }
}
