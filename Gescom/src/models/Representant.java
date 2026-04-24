package models;

import java.util.List;

public class Representant {
    private int idRepresentant;
    private String nom;
    private String prenom;
    private Double caRepresentant;

    private List<Client> lesClients;

    public Representant(int idRepresentant, String nom, String prenom, List<Client> lesClients) {
        this.idRepresentant = idRepresentant;
        this.nom = nom;
        this.prenom = prenom;
        this.lesClients = lesClients;
        this.caRepresentant = 0.0;
    }

    public Client getClientById(int idClient) {
        if (this.lesClients != null) {
            for (Client unClient : this.lesClients) {
                if (unClient.getIdClient() == idClient) {
                    return unClient;
                }
            }
        }
        return null;
    }

    public int getIdRepresentant() { return idRepresentant; }
    public void setIdRepresentant(int idRepresentant) { this.idRepresentant = idRepresentant; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }

    public Double getCaRepresentant() { return caRepresentant; }
    public void setCaRepresentant(Double caRepresentant) { this.caRepresentant = caRepresentant; }

    public List<Client> getLesClients() { return lesClients; }
    public void setLesClients(List<Client> lesClients) { this.lesClients = lesClients; }
}