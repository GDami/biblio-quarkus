package biblio.api;

import io.quarkus.security.credential.PasswordCredential;
import io.quarkus.security.identity.SecurityIdentity;
import io.quarkus.security.identity.request.UsernamePasswordAuthenticationRequest;
import io.quarkus.security.jpa.runtime.JpaIdentityProvider;
import io.smallrye.jwt.build.Jwt;
import jakarta.persistence.EntityManager;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import biblio.dto.request.AuthRequest;
import biblio.dto.response.AuthResponse;

@Path("/api/auth")
public class AuthResource {
    private final JpaIdentityProvider identityProvider;
    private final EntityManager entityManager;

    public AuthResource(JpaIdentityProvider identityProvider, EntityManager entityManager) {
        this.identityProvider = identityProvider;
        this.entityManager = entityManager;
    }

    @POST
    public AuthResponse auth(AuthRequest request) {
        UsernamePasswordAuthenticationRequest authRequest = new UsernamePasswordAuthenticationRequest(
                request.login(), // Le nom d'utilisateur
                new PasswordCredential(request.password().toCharArray()) // Le mot de passe
        );

        // Authentification de l'utilisateur
        SecurityIdentity securityIdentity = this.identityProvider.authenticate(this.entityManager, authRequest);

        // Si tout est OK, on peut générer le jeton
        String jwt = Jwt
                .issuer("biblio-quarkus")
                .upn(request.login()) // User Principal Name ==> le nom d'utilisateur
                .groups(securityIdentity.getRoles()) // Liste des rôles de l'utilisateur connecté, avec @Roles et
                                                     // @UserDefinition
                .sign() // Signer le jeton JWT
        ;

        return new AuthResponse(true, jwt);
    }
}
