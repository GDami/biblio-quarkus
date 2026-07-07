package biblio.dto.request;

import biblio.model.Auteur;
import biblio.model.Collection;
import biblio.model.Editeur;
import jakarta.validation.constraints.NotBlank;

public record CreateOrUpdateLivreRequest(@NotBlank String titre, @NotBlank String resume, @NotBlank int annee,
        @NotBlank Editeur editeur, @NotBlank Auteur auteur, @NotBlank Collection collection) {

}