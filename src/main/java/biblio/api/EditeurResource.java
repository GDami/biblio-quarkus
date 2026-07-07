package biblio.api;

import java.util.List;
import java.util.Map;

import biblio.dto.request.CreateOrUpdateEditeurRequest;
import biblio.dto.response.EditeurResponse;
import biblio.model.Editeur;
import biblio.repo.EditeurRepository;
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

@Path("/api/editeur")
public class EditeurResource {

    @Inject
    private EditeurRepository repo;

    @GET
    public List<EditeurResponse> findAll() {
        return repo.findAll()
            .stream()
            .map(EditeurResponse::convert)
            .toList()
        ;
    }

    @GET
    @Path("/{id}")
    public EditeurResponse findById(@PathParam("id") Integer id) {
        Editeur editeur = repo.findByIdOptional(id).orElseThrow(NotFoundException::new);

        return EditeurResponse.convert(editeur);
    }

    @POST
    @Transactional
    public Response create(CreateOrUpdateEditeurRequest request) {
        Editeur editeur = new Editeur();

        editeur.setNom(request.nom());
        editeur.setPays(request.pays());
        
        repo.persist(editeur);
        
        return Response.status(Response.Status.CREATED)
            .entity(Map.of("id", editeur.getId()))
            .build()
        ;
    }
    
    @PUT
    @Path("/{id}")
    @Transactional
    public Response update(@PathParam("id") Integer id, CreateOrUpdateEditeurRequest request) {
        Editeur editeur = repo.findByIdOptional(id).orElseThrow(NotFoundException::new);
        
        editeur.setNom(request.nom());
        editeur.setPays(request.pays());
        
        repo.persist(editeur);
        
        return Response.ok(Map.of("id", editeur.getId())).build();
    }
    
    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deleteById(@PathParam("id") Integer id) {
        repo.deleteById(id);

        return Response.noContent().build();
    }

}
