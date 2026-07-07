package biblio.dto.request;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;

public record CreateOrUpdateLivreRequest(@NotBlank String nom, @NotBlank String resume, @NotBlank LocalDate publication,
        @NotBlank Integer editeurId, @NotBlank Integer auteurId, @NotBlank Integer collectionId) {

}