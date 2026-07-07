package biblio.api;

import java.util.List;
import java.util.Map;

import biblio.dto.request.CreateOrUpdateLivreRequest;
import biblio.dto.response.LivreResponse;
import biblio.model.Auteur;
import biblio.model.Collection;
import biblio.model.Editeur;
import biblio.model.Livre;
import biblio.repo.AuteurRepository;
import biblio.repo.CollectionRepository;
import biblio.repo.EditeurRepository;
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

    @Inject
    private AuteurRepository auteurRepo;

    @Inject
    private EditeurRepository editeurRepo;

    @Inject
    private CollectionRepository collectionRepo;

    @GET
    public List<LivreResponse> findAll() {
        return repo.findAll()
                .stream()
                .map(LivreResponse::convert)
                .toList();
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

        Auteur auteur = auteurRepo.findByIdOptional(request.auteurId()).orElseThrow(NotFoundException::new);
        Editeur editeur = editeurRepo.findByIdOptional(request.editeurId()).orElseThrow(NotFoundException::new);
        Collection collection = collectionRepo.findByIdOptional(request.collectionId()).orElseThrow(NotFoundException::new);

        livre.setNom(request.nom());
        livre.setResume(request.resume());
        livre.setPublication(request.publication());
        livre.setCollection(collection);
        livre.setAuteur(auteur);
        livre.setEditeur(editeur);

        repo.persist(livre);

        return Response.status(Response.Status.CREATED)
                .entity(Map.of("id", livre.getId()))
                .build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response update(@PathParam("id") Integer id, CreateOrUpdateLivreRequest request) {
        Livre livre = repo.findByIdOptional(id).orElseThrow(NotFoundException::new);

        Auteur auteur = auteurRepo.findByIdOptional(request.auteurId()).orElseThrow(NotFoundException::new);
        Editeur editeur = editeurRepo.findByIdOptional(request.editeurId()).orElseThrow(NotFoundException::new);
        Collection collection = collectionRepo.findByIdOptional(request.collectionId()).orElseThrow(NotFoundException::new);

        livre.setNom(request.nom());
        livre.setResume(request.resume());
        livre.setPublication(request.publication());
        livre.setCollection(collection);
        livre.setAuteur(auteur);
        livre.setEditeur(editeur);

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
