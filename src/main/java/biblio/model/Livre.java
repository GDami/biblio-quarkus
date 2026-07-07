package biblio.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="livre")
public class Livre {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    
    @Column(length=100, nullable=false)
    private String titre;
    
    @Column(length=2000, nullable=false)
    private String resume;
    
    @Column(nullable=false)
    private int annee;

    @ManyToOne
    @JoinColumn(name="editeur", nullable=false)
    private Editeur editeur;
    
    @ManyToOne
    @JoinColumn(name="auteur", nullable=false)
    private Auteur auteur;
    
    @ManyToOne
    @JoinColumn(name="collection")
    private Collection collection;

    @OneToMany(mappedBy = "livre")
    private List<Avis> avis;

    public Livre() {}

    public Livre(Integer id, String titre, String resume, int annee, Editeur editeur, Auteur auteur,
            Collection collection) {
        this.id = id;
        this.titre = titre;
        this.resume = resume;
        this.annee = annee;
        this.editeur = editeur;
        this.auteur = auteur;
        this.collection = collection;
    }



    public Integer getId() {
        return id;
    }

    public Livre(Integer id, String titre, String resume, int annee) {
        this.id = id;
        this.titre = titre;
        this.resume = resume;
        this.annee = annee;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }

    public Editeur getEditeur() {
        return editeur;
    }

    public void setEditeur(Editeur editeur) {
        this.editeur = editeur;
    }

    public Auteur getAuteur() {
        return auteur;
    }

    public void setAuteur(Auteur auteur) {
        this.auteur = auteur;
    }

    public Collection getCollection() {
        return collection;
    }

    public void setCollection(Collection collection) {
        this.collection = collection;
    }

    public List<Avis> getAvis() {
        return avis;
    }

    public void setAvis(List<Avis> avis) {
        this.avis = avis;
    }
}
