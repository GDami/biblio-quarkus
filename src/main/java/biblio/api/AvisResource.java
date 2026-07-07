package biblio.api;

import java.util.List;
import java.util.Map;

import biblio.dto.request.CreateOrUpdateAvisRequest;
import biblio.dto.response.AvisResponse;
import biblio.model.Avis;
import biblio.repo.AvisRepository;
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

@Path("/api/avis")
public class AvisResource {

    @Inject
    private AvisRepository repo;

    @GET
    public List<AvisResponse> findAll() {
        return repo.findAll()
                .stream()
                .map(AvisResponse::convert)
                .toList();
    }

    @GET
    @Path("/{id}")
    public AvisResponse findById(@PathParam("id") Integer id) {
        Avis avis = repo.findByIdOptional(id).orElseThrow(NotFoundException::new);

        return AvisResponse.convert(avis);
    }

    @POST
    @Transactional
    public Response create(CreateOrUpdateAvisRequest request) {
        Avis avis = new Avis();

        avis.setNote(request.note());
        avis.setCommentaire(request.commentaire());
        avis.setDateAvis(request.dateAvis());
        avis.setLivre(request.livre());

        repo.persist(avis);

        return Response.status(Response.Status.CREATED)
                .entity(Map.of("id", avis.getId()))
                .build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response update(@PathParam("id") Integer id, CreateOrUpdateAvisRequest request) {
        Avis avis = repo.findByIdOptional(id).orElseThrow(NotFoundException::new);

        avis.setNote(request.note());
        avis.setCommentaire(request.commentaire());
        avis.setDateAvis(request.dateAvis());
        avis.setLivre(request.livre());

        repo.persist(avis);

        return Response.ok(Map.of("id", avis.getId())).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deleteById(@PathParam("id") Integer id) {
        repo.deleteById(id);

        return Response.noContent().build();
    }

}
