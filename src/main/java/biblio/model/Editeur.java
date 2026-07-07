package biblio.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "editeur")
public class Editeur {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 50, nullable = false)
    private String nom;

    @Column(length = 50, nullable = false)
    private String pays;

    @OneToMany(mappedBy = "editeur")
    private List<Livre> livres;

        public Editeur() {
        }


        public Editeur(Integer id, String nom, String pays) {
            this.id = id;
            this.nom = nom;
            this.pays = pays;
        }


        public Integer getId() {
            return id;
        }

        public String getNom() {
            return nom;
        }

        public String getPays() {
            return pays;
        }


        public void setId(Integer id) {
            this.id = id;
        }

        public void setNom(String nom) {
            this.nom = nom;
        }

        public void setPays(String pays) {
            this.pays = pays;
        }


        public List<Livre> getLivres() {
			return livres;
		}


		public void setLivres(List<Livre> livres) {
			this.livres = livres;
		}


		@Override
        public String toString() {
            return "Editeur [id=" + id + ", nom=" + nom + ", pays=" + pays + "]";
        }

}
