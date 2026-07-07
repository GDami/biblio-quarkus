package biblio.repo;

import java.util.Optional;

import biblio.model.Utilisateur;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UtilisateurRepository implements PanacheRepositoryBase<Utilisateur, Integer> {

    public Optional<Utilisateur> findByUsername(String username){
        return find("username", username).firstResultOptional();
    }
}
