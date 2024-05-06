package lt.vu.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@NamedQueries({
        @NamedQuery(name = "Sponsor.findAll", query = "select s from Sponsor as s")
})
@Table(name = "SPONSOR")
@Getter @Setter
public class Sponsor implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(max = 50)
    @Column(name = "NAME")
    private String name;

    @Column(name = "JERSEY_NUMBER")
    private Integer jerseyNumber;

    // THE MANY TO MANY RELATION!!!!-------------------------------------------
    @ManyToMany(mappedBy = "sponsors")
    private List<Team> teams;

    @Version
    @Column(name = "OPT_LOCK_VERSION")
    private Integer version;

    public Sponsor() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sponsor player = (Sponsor) o;
        return Objects.equals(id, player.id) &&
                Objects.equals(name, player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
