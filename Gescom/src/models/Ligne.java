package models;

public class Ligne {
    private int qteCommande;

    private Article unArticle;

    public Ligne(Article unArticle, int qteCommande) {
        this.unArticle = unArticle;
        this.qteCommande = qteCommande;
    }

    public int getQteCommande() { return qteCommande; }
    public void setQteCommande(int qteCommande) { this.qteCommande = qteCommande; }

    public Article getUnArticle() { return unArticle; }
    public void setUnArticle(Article unArticle) { this.unArticle = unArticle; }
}