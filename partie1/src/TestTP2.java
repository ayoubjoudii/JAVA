import java.util.Scanner;

public class TestTP2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Combien de films souhaitez creer ? ");
        int n = sc.nextInt();
        sc.nextLine();

        partie1.Film[] films = new partie1.Film[n];

        for (int i = 0; i < n; i++) {
            System.out.println("\n Film " + (i + 1) + " ");
            films[i] = new partie1.Film();
            films[i].ajouterFilm();
        }

        System.out.println("\n Liste des films ");
        for (int i = 0; i < n; i++) {
            System.out.println("Film " + (i + 1) + " :");
            films[i].afficherFilm();
        }
    }
}