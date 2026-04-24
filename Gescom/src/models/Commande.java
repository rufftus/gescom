package models;

import services.BdD;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Commande {
    private int idCommande;
    private Date dateCommande;
    private List<Ligne> lesLignes;

    public Commande(int idCommande, Date dateCommande) {
        this.idCommande = idCommande;
        this.dateCommande = dateCommande;
        setLesLignes(); // Instanciation via mutateur privé
    }

    private void setLesLignes() {
        this.lesLignes = new ArrayList<>();
    }

    public int getIdCommande() { return idCommande; }
    public void setIdCommande(int idCommande) { this.idCommande = idCommande; }

    public Date getDateCommande() { return dateCommande; }
    public void setDateCommande(Date dateCommande) { this.dateCommande = dateCommande; }

    public List<Ligne> getLesLignes() { return lesLignes; }

    public void ajouterLigne(Article unArticle, int qteCommande) {
        if (this.lesLignes == null) {
            setLesLignes();
        }
        Ligne uneLigne = new Ligne(unArticle, qteCommande);
        this.lesLignes.add(uneLigne);
    }

    public Ligne chercherLigne(int idArticle, BdD bdd) {
        if (this.lesLignes != null) {
            for (Ligne uneLigne : this.lesLignes) {
                if (uneLigne.getUnArticle().getIdArticle() == idArticle) {
                    return uneLigne;
                }
            }
        }
        return null;
    }

    public void supprimerLigne(Ligne ligneASupprimer) {
        if (this.lesLignes != null && ligneASupprimer != null) {
            this.lesLignes.remove(ligneASupprimer);
        }
    }

    public double valoriserCommande() {
        double total = 0.0;
        if (this.lesLignes != null) {
            for (Ligne uneLigne : this.lesLignes) {
                total += (uneLigne.getQteCommande() * uneLigne.getUnArticle().getPrix());
            }
        }
        return total;
    }
}