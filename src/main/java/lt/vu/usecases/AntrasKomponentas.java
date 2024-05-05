package lt.vu.usecases;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Date;

@Named
 @ApplicationScoped
public class AntrasKomponentas implements Serializable {

    public String sakykLabas() {
        return "Labas2 " + new Date() + " " + toString();
    }

    @PostConstruct
    public void init() {
        System.out.println(toString() + " constructed2.");
    }

    @PreDestroy
    public void aboutToDie() {
        System.out.println(toString() + " ready to die2.");
    }
}