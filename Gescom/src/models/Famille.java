package models;
public class Famille {
    private int idFamille;
    private String libFamille;

    public Famille(int idFamille, String libFamille) {
        this.idFamille = idFamille;
        this.libFamille = libFamille;
    }
    public int getIdFamille() { return idFamille; }
    public void setIdFamille(int idFamille) { this.idFamille = idFamille; }

    public String getLibFamille() { return libFamille; }
    public void setLibFamille(String libFamille) { this.libFamille = libFamille; }
}