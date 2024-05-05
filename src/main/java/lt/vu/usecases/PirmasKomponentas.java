package lt.vu.usecases;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Date;

@Named
 @ApplicationScoped
public class PirmasKomponentas implements Serializable {
    private AntrasKomponentas antras;

    @Inject
    public PirmasKomponentas(AntrasKomponentas antras) {
        this.antras = antras;
    }

    public String sakykLabas() {
        System.out.println();
        return "Labas " + antras.getClass().getName() + new Date() + " " + toString();
    }

    @PostConstruct
    public void init() {
        System.out.println(toString() + " constructed.");
    }

    @PreDestroy
    public void aboutToDie() {
        System.out.println(toString() + " ready to die.");
    }
}