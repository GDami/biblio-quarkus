package biblio.dto.request;

import java.time.LocalDate;

import biblio.model.Livre;
import jakarta.validation.constraints.NotBlank;

public record CreateOrUpdateAvisRequest(@NotBlank Integer note, @NotBlank String commentaire,
        @NotBlank LocalDate dateAvis, Livre livre) {

}