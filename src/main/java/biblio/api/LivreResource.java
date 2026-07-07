package biblio.api;

import java.util.List;
import java.util.Map;

import biblio.model.Livre;
import biblio.repo.LivreRepository;
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

@Path("/api/livre")
public class LivreResource {

    @Inject
    private LivreRepository repo;

    @GET
    public List<LivreResponse> findAll() {
        return repo.findAll()
            .stream()
            .map(LivreResponse::convert)
            .toList()
        ;
    }

    @GET
    @Path("/{id}")
    public LivreResponse findById(@PathParam("id") Integer id) {
        Livre livre = repo.findByIdOptional(id).orElseThrow(NotFoundException::new);

        return LivreResponse.convert(livre);
    }

    @POST
    @Transactional
    public Response create(CreateOrUpdateLivreRequest request) {
        Livre livre = new Livre();

        livre.setTitre(request.titre());
        livre.setResume(request.resume());
        livre.setAnnee(request.annee());
        livre.setCollection(request.collection());
        livre.setAuteur(request.auteur());
        livre.setEditeur(request.nationalite());
        livre.setAvis(request.nationalite());
        
        repo.persist(livre);
        
        return Response.status(Response.Status.CREATED)
        .entity(Map.of("id", livre.getId()))
        .build()
        ;
    }
    
    @PUT
    @Path("/{id}")
    @Transactional
    public Response update(@PathParam("id") Integer id, CreateOrUpdateLivreRequest request) {
        Livre livre = repo.findByIdOptional(id).orElseThrow(NotFoundException::new);
        
        livre.setTitre(request.titre());
        livre.setResume(request.resume());
        livre.setAnnee(request.annee());
        livre.setCollection(request.collection());
        livre.setAuteur(request.auteur());
        livre.setEditeur(request.nationalite());
        livre.setAvis(request.nationalite());
        
        repo.persist(livre);
        
        return Response.ok(Map.of("id", livre.getId())).build();
    }
    
    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deleteById(@PathParam("id") Integer id) {
        repo.deleteById(id);

        return Response.noContent().build();
    }

}
