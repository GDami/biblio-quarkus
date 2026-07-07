package biblio.dto.request;

import jakarta.validation.constraints.NotBlank;

public record CreateOrUpdateAuteurRequest(@NotBlank String nom, @NotBlank String prenom, @NotBlank String nationalite) {

}