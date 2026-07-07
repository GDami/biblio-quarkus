package biblio.dto.response;

import biblio.model.Auteur;
import biblio.model.Collection;
import biblio.model.Editeur;
import biblio.model.Livre;

public record LivreResponse(int id, String titre, String resume, int annee, Editeur editeur, Auteur auteur,
        Collection collection) {
    public static LivreResponse convert(Livre livre) {
        return new LivreResponse(livre.getId(), livre.getTitre(), livre.getResume(), livre.getAnnee(),
                livre.getEditeur(), livre.getAuteur(), livre.getCollection());
    }
}