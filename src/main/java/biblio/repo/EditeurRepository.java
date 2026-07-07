package biblio.repo;

import biblio.model.Editeur;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class EditeurRepository implements PanacheRepositoryBase<Editeur, Integer> {

}
