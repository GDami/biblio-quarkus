package biblio.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="avis")
public class Avis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer note;

    @Column(length = 255, nullable = false)
    private String commentaire;

    @Column(name = "date_avis", nullable = false)
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate dateAvis;

    @ManyToOne
    @JoinColumn(name = "livre", nullable = false)
    private Livre livre;

    public Avis() {}

    public Avis(Integer id, Integer note, String commentaire, LocalDate dateAvis, Livre livre) {
        this.id = id;
        this.note = note;
        this.commentaire = commentaire;
        this.dateAvis = dateAvis;
        this.livre = livre;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getNote() {
        return note;
    }
    public void setNote(Integer note) {
        this.note = note;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public LocalDate getDateAvis() {
        return dateAvis;
    }

    public Livre getLivre() {
        return livre;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public void setDateAvis(LocalDate dateAvis) {
        this.dateAvis = dateAvis;
    }

    public void setLivre(Livre livre) {
        this.livre = livre;
    }

    @Override
    public String toString() {
        return "Avis [id=" + id + ", note=" + note + ", commentaire=" + commentaire + ", dateAvis=" + dateAvis
                + ", livre=" + livre + ", getId()=" + getId() + ", getNote()=" + getNote() + ", getCommentaire()="
                + getCommentaire() + ", getDateAvis()=" + getDateAvis() + ", getLivre()=" + getLivre() + "]";
    }
    


}
