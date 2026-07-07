package biblio.dto.response;

import biblio.model.Auteur;

public record AuteurResponse(int id, String nom, String prenom, String nationalite) {
    public static AuteurResponse convert(Auteur auteur) {
        return new AuteurResponse(auteur.getId(), auteur.getNom(), auteur.getPrenom(), auteur.getNationalite());
    }
}
