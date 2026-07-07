package biblio.repo;

import biblio.model.Collection;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CollectionRepository implements PanacheRepositoryBase<Collection, Integer> {

}
