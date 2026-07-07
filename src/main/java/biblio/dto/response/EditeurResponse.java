package biblio.dto.response;

import biblio.model.Editeur;

public record EditeurResponse(int id, String nom, String pays) {
    public static EditeurResponse convert(Editeur editeur) {
        return new EditeurResponse(editeur.getId(), editeur.getNom(), editeur.getPays());
    }
}