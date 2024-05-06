package lt.vu.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@NamedQueries({
        @NamedQuery(name = "Team.findAll", query = "select t from Team as t")
})
@Table(name = "TEAM")
@Getter @Setter
public class Team {

    public Team(){

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @OneToMany(mappedBy = "team")
    private List<Player> players = new ArrayList<>();

    // THE MANY TO MANY RELATION!!!!-------------------------------------------
    @ManyToMany
    @JoinTable(
            name = "TEAM_SPONSOR", // Name of the join table
            joinColumns = @JoinColumn(name = "TEAM_ID", referencedColumnName = "ID"), // Foreign key for Team in join table
            inverseJoinColumns = @JoinColumn(name = "SPONSOR_ID", referencedColumnName = "ID") // Foreign key for Sponsor in join table
    )
    private List<Sponsor> sponsors;

    @Version
    @Column(name = "OPT_LOCK_VERSION")
    private Integer version;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return Objects.equals(name, team.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name);
    }
}
