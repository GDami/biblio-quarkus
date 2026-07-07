package biblio.api;

import java.util.List;
import java.util.Map;

import biblio.model.Auteur;
import biblio.repo.AuteurRepository;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;

@Path("/api/auteur")
public class AuteurResource {

    @Inject
    private AuteurRepository repo;

    @GET
    public List<AuteurResponse> findAll() {
        return repo.findAll()
            .stream()
            .map(AuteurResponse::convert)
            .toList()
        ;
    }

    @GET
    @Path("/{id}")
    public AuteurResponse findById(@PathParam("id") Integer id) {
        Auteur auteur = repo.findByIdOptional(id).orElseThrow(NotFoundException::new);

        return AuteurResponse.convert(auteur);
    }

    @POST
    @Transactional
    public Response create(CreateOrUpdateAuteurRequest request) {
        Auteur auteur = new Auteur();

        auteur.setNom(request.nom());
        auteur.setPrenom(request.prenom());
        auteur.setNationalite(request.nationalite());
        
        repo.persist(auteur);
        
        return Response.status(Response.Status.CREATED)
            .entity(Map.of("id", auteur.getId()))
            .build()
        ;
    }
    
    @PUT
    @Path("/{id}")
    @Transactional
    public Response update(@PathParam("id") Integer id, CreateOrUpdateAuteurRequest request) {
        Auteur auteur = repo.findByIdOptional(id).orElseThrow(NotFoundException::new);
        
        auteur.setNom(request.nom());
        auteur.setPrenom(request.prenom());
        auteur.setNationalite(request.nationalite());
        
        repo.persist(auteur);
        
        return Response.ok(Map.of("id", auteur.getId())).build();
    }
    
    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deleteById(@PathParam("id") Integer id) {
        repo.deleteById(id);

        return Response.noContent().build();
    }

}
