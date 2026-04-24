package models;

import java.util.*;

public class Client {

    private int idClient;
    private String raisonSociale;
    private double caClient;
    private Categorie uneCategorie;
    private ArrayList<Commande> lesCommandes;

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public String getRaisonSociale() {
        return raisonSociale;
    }

    public void setRaisonSociale(String raisonSociale) {
        this.raisonSociale = raisonSociale;
    }

    public double getCaClient() {
        return caClient;
    }

    public void setCaClient(double caClient) {
        this.caClient = caClient;
    }

    public Categorie getUneCategorie() {
        return uneCategorie;
    }

    public void setUneCategorie(Categorie uneCategorie) {
        this.uneCategorie = uneCategorie;
    }

    public ArrayList<Commande> getLesCommandes() {
        return lesCommandes;
    }

    private void setLesCommandes(ArrayList<Commande> lesCommandes) {
        this.lesCommandes = lesCommandes;
    }

    public Client(int idClient, String raisonSociale, Categorie uneCategorie) {
        this.idClient = idClient;
        this.raisonSociale = raisonSociale;
        this.uneCategorie = uneCategorie;
        this.caClient = 0;
        this.lesCommandes = new ArrayList<Commande>();
    }

    public void ajouterCommande(Commande uneCommande) {
        if (getLesCommandes() == null) {
            setLesCommandes(new ArrayList<Commande>());
        }

        if (!getLesCommandes().contains(uneCommande)) {
            this.lesCommandes.add(uneCommande);
        }
    }

    public void cumulCA() {
        this.caClient = 0;

        for (Commande uneCommande : this.lesCommandes) {
            this.caClient += uneCommande.valoriserCommande();
        }
    }

    public Commande getCommandeById(int idCommande) {
        for (Commande uneCommande : this.lesCommandes) {
            if (uneCommande.getIdCommande() == idCommande) {
                return uneCommande;
            }
        }

        return null;
    }

    public void supprimerCommande(Commande uneCommande) {
        this.lesCommandes.remove(uneCommande);
    }
}