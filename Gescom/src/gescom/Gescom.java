package gescom;

import models.*;
import services.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Date;

public class Gescom {

    static BdD bdd;
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        bdd = new BdD();
        Representant unRepresentant = new Representant(100, "Paul", "Auchon", bdd.getClientsBdD());

        int choix = menu();

        while (choix != 0) {
            switch (choix) {
                case 1: listerClients(unRepresentant); break;
                case 2: afficherArticlesCommandes(unRepresentant); break;
                case 3: rechercherCommande(unRepresentant); break;
                case 4: ajouterCommande(unRepresentant); break;
                case 5: supprimerCommande(unRepresentant); break;
                case 6: supprimerLigne(unRepresentant); break;
                case 7: afficherCaClient(unRepresentant); break;
                case 8: afficherCaClients(unRepresentant); break;
            }
            choix = menu();
        }
    }

    private static int menu() {
        System.out.println("\n--- Menu général ---");
        System.out.println("1..Lister les clients et leurs commandes");
        System.out.println("2..Liste des articles commandés");
        System.out.println("3..Rechercher une commande");
        System.out.println("4..Ajouter une commande");
        System.out.println("5..Supprimer une commande");
        System.out.println("6..Supprimer une ligne d'une commande");
        System.out.println("7..Afficher le CA d'un client");
        System.out.println("8..Afficher le CA de tous les clients");
        System.out.println("0..Quitter");

        System.out.print("\nVotre choix : ");
        return sc.nextInt();
    }

    private static void afficherCaClient(Representant unRepresentant) {
        System.out.print("Id client : ");
        int idClient = sc.nextInt();

        Client unClient = unRepresentant.getClientById(idClient);

        if (unClient != null) {
            unClient.cumulCA();
            System.out.println("CA du client : " + unClient.getCaClient());
        } else {
            System.out.println("Client inexistant.");
        }
    }

    private static void afficherCommandesClient(Representant unRepresentant) {
        System.out.print("Id client : ");
        int idClient = sc.nextInt();

        Client unClient = unRepresentant.getClientById(idClient);

        if (unClient != null && unClient.getLesCommandes() != null) {
            for (Commande uneCommande : unClient.getLesCommandes()) {
                afficherCommande(uneCommande);
            }
        } else {
            System.out.println("Client inexistant ou aucune commande.");
        }
    }

    private static void listerClients(Representant unRepresentant) {
        if (unRepresentant.getLesClients() != null) {
            for (Client unClient : unRepresentant.getLesClients()) {
                System.out.println("\nClient : " + unClient.getIdClient() + " - " + unClient.getRaisonSociale());

                if (unClient.getLesCommandes() != null) {
                    for (Commande uneCommande : unClient.getLesCommandes()) {
                        afficherCommande(uneCommande);
                    }
                }
            }
        }
    }

    private static void supprimerCommande(Representant unRepresentant) {
        System.out.print("Id commande à supprimer : ");
        int idCommande = sc.nextInt();

        if (unRepresentant.getLesClients() != null) {
            for (Client unClient : unRepresentant.getLesClients()) {
                Commande uneCommande = unClient.getCommandeById(idCommande);

                if (uneCommande != null) {
                    unClient.supprimerCommande(uneCommande);
                    System.out.println("Commande supprimée avec succès.");
                    return;
                }
            }
        }
        System.out.println("Commande introuvable.");
    }

    private static void afficherArticlesCommandes(Representant unRepresentant) {
        ArrayList<Article> lesArticles = new ArrayList<Article>();

        if (unRepresentant.getLesClients() != null) {
            for (Client unClient : unRepresentant.getLesClients()) {
                if (unClient.getLesCommandes() != null) {
                    for (Commande uneCommande : unClient.getLesCommandes()) {
                        if (uneCommande.getLesLignes() != null) {
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
            }
        }
    }

    private static void afficherArticle(Article unArticle) {
        System.out.println(
                unArticle.getIdArticle()
                        + " - " + unArticle.getDesignation()
                        + " - Famille: " + unArticle.getUneFamille().getLibFamille()
                        + " - TVA: " + unArticle.getUneTva().getTauxTva() + "%"
        );
    }

    private static void afficherCaClients(Representant unRepresentant) {
        if (unRepresentant.getLesClients() != null) {
            for (Client unClient : unRepresentant.getLesClients()) {
                unClient.cumulCA();
                System.out.println(
                        unClient.getIdClient()
                                + " - " + unClient.getRaisonSociale()
                                + " - CA : " + unClient.getCaClient()
                );
            }
        }
    }

    private static void rechercherCommande(Representant unRepresentant) {
        System.out.print("Id client : ");
        int idClient = sc.nextInt();

        Client unClient = unRepresentant.getClientById(idClient);

        if (unClient != null) {
            System.out.print("Id commande : ");
            int idCommande = sc.nextInt();

            Commande uneCommande = unClient.getCommandeById(idCommande);

            if (uneCommande != null) {
                afficherCommande(uneCommande);
            } else {
                System.out.println("Commande inexistante.");
            }
        } else {
            System.out.println("Client inexistant.");
        }
    }

    private static void supprimerLigne(Representant unRepresentant) {
        System.out.print("Id client : ");
        int idClient = sc.nextInt();

        Client unClient = unRepresentant.getClientById(idClient);

        if (unClient != null) {
            System.out.print("Id commande : ");
            int idCommande = sc.nextInt();

            Commande uneCommande = unClient.getCommandeById(idCommande);

            if (uneCommande != null) {
                System.out.print("Id article à supprimer : ");
                int idArticle = sc.nextInt();

                Ligne ligneTrouvee = uneCommande.chercherLigne(idArticle, bdd);

                if (ligneTrouvee != null) {
                    uneCommande.supprimerLigne(ligneTrouvee);
                    System.out.println("Ligne supprimée !");
                } else {
                    System.out.println("L'article ne figure pas dans cette commande.");
                }
            } else {
                System.out.println("Commande inexistante.");
            }
        } else {
            System.out.println("Client inexistant.");
        }
    }

    private static void ajouterCommande(Representant unRepresentant) {
        System.out.print("Id client : ");
        int idClient = sc.nextInt();

        Client unClient = unRepresentant.getClientById(idClient);

        if (unClient != null) {
            System.out.print("Id de la nouvelle commande : ");
            int idCommande = sc.nextInt();

            // Pour simplifier, on prend automatiquement la date d'aujourd'hui
            Commande uneCommande = new Commande(idCommande, new Date());
            unClient.ajouterCommande(uneCommande);

            System.out.print("Id de l'article à commander : ");
            int idArticle = sc.nextInt();

            Article unArticle = bdd.getArticleBdD(idArticle);

            if (unArticle != null) {
                System.out.print("Quantité commandée : ");
                int qteCommande = sc.nextInt();

                uneCommande.ajouterLigne(unArticle, qteCommande);
                System.out.println("Commande ajoutée avec succès !");
            } else {
                System.out.println("L'article demandé n'existe pas dans la base de données.");
            }
        } else {
            System.out.println("Client inexistant.");
        }
    }

    private static void afficherCommande(Commande uneCommande) {
        System.out.println("\tCommande : " + uneCommande.getIdCommande() + " (Date: " + uneCommande.getDateCommande() + ")");

        if (uneCommande.getLesLignes() != null) {
            for (Ligne uneLigne : uneCommande.getLesLignes()) {
                System.out.println(
                        "\t\t- Article " + uneLigne.getUnArticle().getIdArticle()
                                + " (" + uneLigne.getUnArticle().getDesignation() + ")"
                                + " | Quantité : " + uneLigne.getQteCommande()
                );
            }
        }
    }
}