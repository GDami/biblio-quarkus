package biblio.dto.response;

import java.time.LocalDate;

import biblio.model.Livre;

public record LivreResponse(int id, String nom, String resume, LocalDate publication, String editeurNom, String auteurNom,
        String collectionNom) {
    public static LivreResponse convert(Livre livre) {
        return new LivreResponse(livre.getId(), livre.getNom(), livre.getResume(), livre.getPublication(),
                livre.getEditeur().getNom(), livre.getAuteur().getNom(), livre.getCollection().getNom());
    }
}