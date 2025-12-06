import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import partie2.Film;

public class Projection {
    private Film film;
    private LocalDateTime dateHeureProjection;
    private String salleProjection;


    public Projection(Film film, LocalDateTime dateHeureProjection, String salleProjection) {
        this.film = film;
        this.dateHeureProjection = dateHeureProjection;
        this.salleProjection = salleProjection;
    }


    public void afficherProjection() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        System.out.println("Projection ");
        System.out.println("Film : ");
        film.afficherFilm();
        System.out.println("Date et heure : " + dateHeureProjection.format(formatter));
        System.out.println("Salle : " + salleProjection);
    }

    // Getters and Setters
    public Film getFilm() { return film; }
    public void setFilm(Film film) { this.film = film; }

    public LocalDateTime getDateHeureProjection() { return dateHeureProjection; }
    public void setDateHeureProjection(LocalDateTime dateHeureProjection) { this.dateHeureProjection = dateHeureProjection; }

    public String getSalleProjection() { return salleProjection; }
    public void setSalleProjection(String salleProjection) { this.salleProjection = salleProjection; }
}

