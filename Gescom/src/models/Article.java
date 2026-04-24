package models;

public class Article {
    private int idArticle;
    private String designation;
    private Double caArticle;
    private int qteStock;
    private Double prix;

    // Relations 1..1
    private Tva uneTva;
    private Famille uneFamille;

    public Article(int idArticle, String designation, int qteStock, Double prix, Tva uneTva, Famille uneFamille) {
        this.idArticle = idArticle;
        this.designation = designation;
        this.qteStock = qteStock;
        this.prix = prix;
        this.uneTva = uneTva;
        this.uneFamille = uneFamille;
        this.caArticle = 0.0; // Initialisé à 0
    }

    // --- Getters et Setters ---
    public int getIdArticle() { return idArticle; }
    public void setIdArticle(int idArticle) { this.idArticle = idArticle; }

    public String getDesignation() { return designation; }
    public void setDesignation(String designation) { this.designation = designation; }

    public Double getCaArticle() { return caArticle; }
    public void setCaArticle(Double caArticle) { this.caArticle = caArticle; }

    public int getQteStock() { return qteStock; }
    public void setQteStock(int qteStock) { this.qteStock = qteStock; }

    public Double getPrix() { return prix; }
    public void setPrix(Double prix) { this.prix = prix; }

    public Tva getUneTva() { return uneTva; }
    public void setUneTva(Tva uneTva) { this.uneTva = uneTva; }

    public Famille getUneFamille() { return uneFamille; }
    public void setUneFamille(Famille uneFamille) { this.uneFamille = uneFamille; }
}