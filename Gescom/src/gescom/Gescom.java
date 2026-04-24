package gescom;

import models.*;
import services.*;
import java.util.Scanner;
import java.util.ArrayList;


public class Gescom {

    static BdD bdd;

    public static void main(String[] args) {
        bdd = new BdD();

        Representant unRepresentant = new Representant(100, "Paul", "Auchon", bdd.getClientsBdD());

        int choix = menu();

        while (choix != 0) {
            switch (choix) {
                case 1:
                    listerClients(unRepresentant);
                    break;
                case 2:
                    afficherArticlesCommandes(unRepresentant);
                    break;
                case 3:
                    rechercherCommande(unRepresentant);
                    break;
                case 4:
                    ajouterCommande(unRepresentant);
                    afficherCommandesClient(unRepresentant);
                    break;
                case 5:
                    supprimerCommande(unRepresentant);
                    listerClients(unRepresentant);
                    break;
                case 6:
                    supprimerLigne(unRepresentant);
                    break;
                case 7:
                    afficherCaClient(unRepresentant);
                    break;
                case 8:
                    afficherCaClients(unRepresentant);
                    break;
            }

            choix = menu();
        }
    }

    private static int menu() {
        System.out.println("Menu général");
        System.out.println();
        System.out.println("1..Lister les clients et leurs commandes");
        System.out.println("2..Liste des articles commandés");
        System.out.println("3..Rechercher une commande");
        System.out.println("4..Ajouter une commande");
        System.out.println("5..Supprimer une commande");
        System.out.println("6..Supprimer une ligne d'une commande");
        System.out.println("7..Afficher le CA d'un client");
        System.out.println("8..Afficher le CA de tous les clients");
        System.out.println("0..Quitter");

        Scanner sc = new Scanner(System.in);
        System.out.println("Votre choix : ");
        int choix = sc.nextInt();

        return choix;
    }

    private static void afficherCaClient(Representant unRepresentant) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Id client : ");
        int idClient = sc.nextInt();

        Client unClient = unRepresentant.getClientById(idClient);

        if (unClient != null) {
            unClient.cumulCA();
            System.out.println("CA du client : " + unClient.getCaClient());
        } else {
            System.out.println("Client inexistant");
        }
    }

    private static void afficherCommandesClient(Representant unRepresentant) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Id client : ");
        int idClient = sc.nextInt();

        Client unClient = unRepresentant.getClientById(idClient);

        if (unClient != null) {
            for (Commande uneCommande : unClient.getLesCommandes()) {
                afficherCommande(uneCommande);
            }
        } else {
            System.out.println("Client inexistant");
        }
    }

    private static void listerClients(Representant unRepresentant) {
        for (Client unClient : unRepresentant.getLesClients()) {
            System.out.println("Client : " + unClient.getIdClient() + " - " + unClient.getRaisonSociale());

            for (Commande uneCommande : unClient.getLesCommandes()) {
                afficherCommande(uneCommande);
            }
        }
    }

    private static void supprimerCommande(Representant unRepresentant) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Id commande à supprimer : ");
        int idCommande = sc.nextInt();

        for (Client unClient : unRepresentant.getLesClients()) {
            Commande uneCommande = unClient.getCommandeById(idCommande);

            if (uneCommande != null) {
                unClient.supprimerCommande(uneCommande);
                System.out.println("Commande supprimée");
                break;
            }
        }
    }

    private static void afficherArticlesCommandes(Representant unRepresentant) {
        ArrayList<Article> lesArticles = new ArrayList<Article>();

        for (Client unClient : unRepresentant.getLesClients()) {
            for (Commande uneCommande : unClient.getLesCommandes()) {
                for (Ligne uneLigne : uneCommande.getLesLignes()) {
                    Article unArticle = uneLigne.getUnArticle();

                    if (!lesArticles.contains(unArticle)) {
                        lesArticles.add(unArticle);
                        afficherArticle(unArticle);
                    }
                }
            }
        }
    }

    private static void afficherArticle(Article unArticle) {
        System.out.println(
                unArticle.getIdArticle()
                        + " - " + unArticle.getDesignation()
                        + " - " + unArticle.getUneFamille().getLibFamille()
                        + " - " + unArticle.getUneTva().getTauxTva()
        );
    }

    private static void afficherCaClients(Representant unRepresentant) {
        for (Client unClient : unRepresentant.getLesClients()) {
            unClient.cumulCA();

            System.out.println(
                    unClient.getIdClient()
                            + " - " + unClient.getRaisonSociale()
                            + " - CA : " + unClient.getCaClient()
            );
        }
    }

    private static void rechercherCommande(Representant unRepresentant) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Id client : ");
        int idClient = sc.nextInt();

        Client unClient = unRepresentant.getClientById(idClient);

        if (unClient != null) {
            System.out.println("Id commande : ");
            int idCommande = sc.nextInt();

            Commande uneCommande = unClient.getCommandeById(idCommande);

            if (uneCommande != null) {
                afficherCommande(uneCommande);
            } else {
                System.out.println("Commande inexistante");
            }
        } else {
            System.out.println("Client inexistant");
        }
    }

    private static void supprimerLigne(Representant unRepresentant) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Id client : ");
        int idClient = sc.nextInt();

        Client unClient = unRepresentant.getClientById(idClient);

        if (unClient != null) {
            System.out.println("Id commande : ");
            int idCommande = sc.nextInt();

            Commande uneCommande = unClient.getCommandeById(idCommande);

            if (uneCommande != null) {
                System.out.println("Id article : ");
                int idArticle = sc.nextInt();

                Article unArticle = bdd.getArticleBdD(idArticle);

                if (unArticle != null) {
                    uneCommande.supprimerLigne(unArticle);
                    System.out.println("Ligne supprimée");
                } else {
                    System.out.println("L'article ne figure pas dans cette commande");
                }
            } else {
                System.out.println("Commande inexistante");
            }
        } else {
            System.out.println("Client inexistant");
        }
    }

    private static void ajouterCommande(Representant unRepresentant) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Id client : ");
        int idClient = sc.nextInt();

        Client unClient = unRepresentant.getClientById(idClient);

        if (unClient != null) {
            System.out.println("Id commande : ");
            int idCommande = sc.nextInt();

            System.out.println("Date commande : ");
            String dateCommande = sc.next();

            Commande uneCommande = new Commande(idCommande, new java.util.Date());

            unClient.ajouterCommande(uneCommande);

            System.out.println("Id article : ");
            int idArticle = sc.nextInt();

            Article unArticle = bdd.getArticleBdD(idArticle);

            System.out.println("Quantité commandée : ");
            int qteCommande = sc.nextInt();

            uneCommande.ajouterLigne(unArticle, qteCommande);

            System.out.println("Commande ajoutée");
        } else {
            System.out.println("Client inexistant");
        }
    }

    private static void afficherCommande(Commande uneCommande) {
        System.out.println("Commande : " + uneCommande.getIdCommande());
        System.out.println("Date : " + uneCommande.getDateCommande());

        for (Ligne uneLigne : uneCommande.getLesLignes()) {
            System.out.println(
                    uneLigne.getUnArticle().getIdArticle()
                            + " - " + uneLigne.getUnArticle().getDesignation()
                            + " - Quantité : " + uneLigne.getQteCommande()
            );
        }
    }
}