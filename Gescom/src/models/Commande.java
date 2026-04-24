package models;

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
        this.lesLignes = new ArrayList<>();
    }

    public void ajouterLigne(Article unArticle, int qteCommande) {
        if (lesLignes == null) {
            lesLignes = new ArrayList<>();
        }

        Ligne uneLigne = new Ligne(qteCommande, unArticle);
        lesLignes.add(uneLigne);
    }

    public void supprimerLigne(Article unArticle) {
        if (lesLignes != null) {
            Ligne ligneASupprimer = null;

            for (Ligne uneLigne : lesLignes) {
                if (uneLigne.getUnArticle().getIdArticle() == unArticle.getIdArticle()) {
                    ligneASupprimer = uneLigne;
                }
            }

            if (ligneASupprimer != null) {
                lesLignes.remove(ligneASupprimer);
            }
        }
    }

    public double valoriserCommande() {
        double total = 0;

        if (lesLignes != null) {
            for (Ligne uneLigne : lesLignes) {
                total += uneLigne.getQteCommande() * uneLigne.getUnArticle().getPrix();
            }
        }

        return total;
    }

    public int getIdCommande() {
        return idCommande;
    }

    public Date getDateCommande() {
        return dateCommande;
    }

    public List<Ligne> getLesLignes() {
        return lesLignes;
    }

    public void setLesLignes(List<Ligne> lesLignes) {
        this.lesLignes = lesLignes;
    }
}