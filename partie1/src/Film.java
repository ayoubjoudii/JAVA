import java.util.Scanner;

public class Film {
    private int id;
    private String titre;
    private String acteurPrincipal;
    private int anneeDeProduction;

    private static int count = 0;

    public Film() {
        count++;
        this.id = count;
        this.titre = "Inconnu";
        this.acteurPrincipal = "Inconnu";
        this.anneeDeProduction = 0;
    }

    public Film(String titre, String acteurPrincipal, int anneeDeProduction) {
        count++;
        this.id = count;
        this.titre = titre;
        this.acteurPrincipal = acteurPrincipal;
        this.anneeDeProduction = anneeDeProduction;
    }

    public void ajouterFilm() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Entrez le titre du film : ");
        this.titre = sc.nextLine();

        System.out.print("Entrez le nom de l'acteur principal : ");
        this.acteurPrincipal = sc.nextLine();

        System.out.print("Entrez l'annee de production : ");
        this.anneeDeProduction = sc.nextInt();
    }

    public void afficherFilm() {
        System.out.println("ID : " + id);
        System.out.println("Titre : " + titre);
        System.out.println("Acteur principal : " + acteurPrincipal);
        System.out.println("Annee de production : " + anneeDeProduction);
    }
}

