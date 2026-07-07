package biblio.dto.response;

import biblio.model.Collection;

public record CollectionResponse(int id, String nom) {
    public static CollectionResponse convert(Collection collection) {
        return new CollectionResponse(collection.getId(), collection.getNom());
    }
}