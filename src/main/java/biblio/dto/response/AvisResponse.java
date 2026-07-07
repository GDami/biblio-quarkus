package biblio.dto.response;

import java.time.LocalDate;

import biblio.model.Avis;

public record AvisResponse(int id, int note, String commentaire, LocalDate date, String livreNom) {
    public static AvisResponse convert(Avis avis) {
        return new AvisResponse(avis.getId(), avis.getNote(), avis.getCommentaire(), avis.getDateAvis(),
                avis.getLivre().getNom());
    }
}