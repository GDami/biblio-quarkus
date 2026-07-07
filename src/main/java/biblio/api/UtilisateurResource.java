package biblio.api;

import java.util.Map;

import biblio.dto.request.InscriptionRequest;
import biblio.model.Utilisateur;
import biblio.repo.UtilisateurRepository;
import io.quarkus.elytron.security.common.BcryptUtil;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("/api/utilisateur")
public class UtilisateurResource {

    @Inject
    private UtilisateurRepository repo;

    @POST
    @Path("/inscription")
    @Transactional
    public Response create(InscriptionRequest request) {
        Utilisateur utilisateur = new Utilisateur();

        utilisateur.setLogin(request.login());
        utilisateur.setPassword(BcryptUtil.bcryptHash(request.password()));

        repo.persist(utilisateur);

        return Response.status(Response.Status.CREATED)
                .entity(Map.of("id", utilisateur.getId()))
                .build();
    }

}
