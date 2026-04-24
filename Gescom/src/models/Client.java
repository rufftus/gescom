package models;

import java.util.ArrayList;
import java.util.List;

public class Client {
    private int idClient;
    private String raisonSociale;
    private double caClient;

    private Categorie uneCategorie;
    private List<Commande> lesCommandes;

    public Client(int idClient, String raisonSociale, Categorie uneCategorie) {
        this.idClient = idClient;
        this.raisonSociale = raisonSociale;
        this.uneCategorie = uneCategorie;
        this.caClient = 0.0;
        setLesCommandes(); // Instanciation via setter privé
    }

    private void setLesCommandes() {
        this.lesCommandes = new ArrayList<>();
    }

    public int getIdClient() { return idClient; }
    public void setIdClient(int idClient) { this.idClient = idClient; }

    public String getRaisonSociale() { return raisonSociale; }
    public void setRaisonSociale(String raisonSociale) { this.raisonSociale = raisonSociale; }

    public double getCaClient() { return caClient; }
    public void setCaClient(double caClient) { this.caClient = caClient; }

    public Categorie getUneCategorie() { return uneCategorie; }
    public void setUneCategorie(Categorie uneCategorie) { this.uneCategorie = uneCategorie; }

    public List<Commande> getLesCommandes() { return lesCommandes; }

    public void ajouterCommande(Commande uneCommande) {
        if (this.lesCommandes == null) {
            setLesCommandes();
        }
        if (!this.lesCommandes.contains(uneCommande)) {
            this.lesCommandes.add(uneCommande);
        }
    }

    public void cumulCA() {
        this.caClient = 0.0;
        if (this.lesCommandes != null) {
            for (Commande uneCommande : this.lesCommandes) {
                this.caClient += uneCommande.valoriserCommande();
            }
        }
    }

    public Commande getCommandeById(int idCommande) {
        if (this.lesCommandes != null) {
            for (Commande uneCommande : this.lesCommandes) {
                if (uneCommande.getIdCommande() == idCommande) {
                    return uneCommande;
                }
            }
        }
        return null;
    }

    public void supprimerCommande(Commande uneCommande) {
        if (this.lesCommandes != null && uneCommande != null) {
            this.lesCommandes.remove(uneCommande);
        }
    }
}