package biblio.dto.response;

import java.time.LocalDate;

import biblio.model.Avis;
import biblio.model.Livre;

public record AvisResponse(int id, int note, String commentaire, LocalDate dateAvis, Livre livre) {
    public static AvisResponse convert(Avis avis) {
        return new AvisResponse(avis.getId(), avis.getNote(), avis.getCommentaire(), avis.getDateAvis(),
                avis.getLivre());
    }
}