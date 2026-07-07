package biblio.api;

import java.util.List;
import java.util.Map;

import biblio.model.Collection;
import biblio.repo.CollectionRepository;
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

@Path("/api/collection")
public class CollectionResource {

    @Inject
    private CollectionRepository repo;

    @GET
    public List<CollectionResponse> findAll() {
        return repo.findAll()
            .stream()
            .map(CollectionResponse::convert)
            .toList()
        ;
    }

    @GET
    @Path("/{id}")
    public CollectionResponse findById(@PathParam("id") Integer id) {
        Collection collection = repo.findByIdOptional(id).orElseThrow(NotFoundException::new);

        return CollectionResponse.convert(collection);
    }

    @POST
    @Transactional
    public Response create(CreateOrUpdateCollectionRequest request) {
        Collection collection = new Collection();

        collection.setNom(request.nom());
        
        repo.persist(collection);
        
        return Response.status(Response.Status.CREATED)
            .entity(Map.of("id", collection.getId()))
            .build()
        ;
    }
    
    @PUT
    @Path("/{id}")
    @Transactional
    public Response update(@PathParam("id") Integer id, CreateOrUpdateCollectionRequest request) {
        Collection collection = repo.findByIdOptional(id).orElseThrow(NotFoundException::new);
        
        collection.setNom(request.nom());
        
        repo.persist(collection);
        
        return Response.ok(Map.of("id", collection.getId())).build();
    }
    
    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deleteById(@PathParam("id") Integer id) {
        repo.deleteById(id);

        return Response.noContent().build();
    }

}
