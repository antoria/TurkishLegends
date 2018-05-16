package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.entity.User;
import model.manager.LoginManager;

import java.io.IOException;
import java.sql.SQLException;

public class Login extends Controller
{
    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton;

    @FXML
    private Button registerButton;

    @FXML
    private Label errorLabel;

    public static User CURRENT_USER = null;


    @FXML
    public void verifyCredentials(ActionEvent event) throws IOException, SQLException
    {
        String email = emailField.getText();
        /*
        No security yet, everything is in plain text
         */
        String password = passwordField.getText();
        if(emailField.getText().trim().isEmpty() || passwordField.getText().trim().isEmpty())
        {
            errorLabel.setText("Invalid credentials.");
        }
        else
            {
            LoginManager lm = new LoginManager();
        /*
        Try to log in
         */
            CURRENT_USER = lm.login(email, password);
            if (CURRENT_USER != null) {
                if (CURRENT_USER.getClass().getSimpleName().equals("Customer")) {
                    switchStage(event, "/view/customer.fxml");
                } else {
                    switchStage(event, "/view/staff.fxml");
                }

            } else {
                errorLabel.setText("Invalid credentials.");
            }
        }
    }


    @FXML
    public void gotoRegister(ActionEvent event) throws IOException
    {
        switchStage(event, "/view/register.fxml");
    }
}
