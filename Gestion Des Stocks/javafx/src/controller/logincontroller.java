package controller;

import dao.userdao;
import model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.IOException;
public class logincontroller {
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label errorLabel;
    @FXML
    private Button loginButton;
    private userdao userDAO;
    public void initialize() {
        userDAO = new userdao();
        errorLabel.setText("");
        if (!userDAO.userExists("admin")) {
            User defaultUser = new User("admin", "admin", "Administrateur", "admin");
            userDAO.addUser(defaultUser);
        }
    }
    @FXML
    private void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();
        if (username.isEmpty() || password.isEmpty()) {
            errorLabel.setText("Veuillez remplir tous les champs");
            errorLabel.setStyle("-fx-text-fill: red;");
            return;
        }
        User user = userDAO.authenticate(username, password);
        if (user != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/products.fxml"));
                Parent root = loader.load();
                productcontroller controller = loader.getController();
                controller.setCurrentUser(user);
                Stage stage = (Stage) loginButton.getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("Gestion de Stock - " + user.getFullName());
                stage.show();
            } catch (IOException e) {
                errorLabel.setText("Erreur lors du chargement de l'application");
                errorLabel.setStyle("-fx-text-fill: red;");
                e.printStackTrace();
            }
        } else {
            errorLabel.setText("Nom d'utilisateur ou mot de passe incorrect");
            errorLabel.setStyle("-fx-text-fill: red;");
        }
    }
    @FXML
    private void handleCancel() {
        Stage stage = (Stage) loginButton.getScene().getWindow();
        stage.close();
    }
}

