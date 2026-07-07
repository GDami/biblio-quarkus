package biblio.repo;

import biblio.model.Avis;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AvisRepository implements PanacheRepositoryBase<Avis, Integer> {

}
