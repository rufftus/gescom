package models;

public class Categorie {
    private int idCategorie;
    private String libCategorie;

    public Categorie(int idCategorie, String libCategorie) {
        this.idCategorie = idCategorie;
        this.libCategorie = libCategorie;
    }

    public int getIdCategorie() { return idCategorie; }
    public void setIdCategorie(int idCategorie) { this.idCategorie = idCategorie; }

    public String getLibCategorie() { return libCategorie; }
    public void setLibCategorie(String libCategorie) { this.libCategorie = libCategorie; }
}