package biblio.dto.request;

import jakarta.validation.constraints.NotBlank;

public record CreateOrUpdateCollectionRequest(@NotBlank String nom) {

}