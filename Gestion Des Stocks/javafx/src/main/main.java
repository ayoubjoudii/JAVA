package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import dao.dbconnection;
public class main extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            if (!dbconnection.testConnection()) {
                showError("Impossible de se connecter à la base de données.\n" +
                        "Veuillez vérifier que MySQL est démarré et que la base de données 'gestion_stock' existe.");
                return;
            }
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/login.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            primaryStage.setTitle("Gestion de Stock - Connexion");
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
            showError("Erreur lors du chargement de l'application: " + e.getMessage());
        }
    }
    @Override
    public void stop() {
        dbconnection.closeConnection();
        System.out.println("Application fermée.");
    }
    private void showError(String message) {
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(
                javafx.scene.control.Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText("Erreur de connexion");
        alert.setContentText(message);
        alert.showAndWait();
    }
    public static void main(String[] args) {
        launch(args);
    }
}


