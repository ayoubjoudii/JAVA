import java.util.ArrayList;
import java.util.List;

public class Cinema {
    private String nom;
    private List<Projection> projections;

    public Cinema(String nom) {
        this.nom = nom;
        this.projections = new ArrayList<>();
    }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public List<Projection> getProjections() { return projections; }

    public void ajouterProjection(Projection p) {
        if (!projections.contains(p)) { // ensure projection not already added
            p.setMyCinema(this); // set cinema reference
            projections.add(p);
            System.out.println("Projection ajoute au cinema " + nom);
        } else {
            System.out.println("Cette projection est deja associe a ce cinema !");
        }
    }

    public void afficherProjections() {
        System.out.println("\nProjections programmes au cinema " + nom);
        if (projections.isEmpty()) {
            System.out.println("Aucune projection programme.");
        } else {
            for (Projection p : projections) {
                p.afficherProjection();
            }
        }
    }
}
