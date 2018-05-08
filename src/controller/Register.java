package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.manager.LoginManager;

import java.io.IOException;
import java.sql.SQLException;

public class Register extends Controller
{
    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField phoneField;

    @FXML
    private TextField addressField;

    @FXML
    private Button registerButton;

    @FXML
    private Label errorLabel;

    @FXML
    public void createAccount(ActionEvent event)
    {
        /*
        Retrieving the information from the input
         */
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String email = emailField.getText();
        String password = passwordField.getText();
        String phone = phoneField.getText();
        String address = addressField.getText();

        boolean correctInfo = true;
        errorLabel.setText("");

        /*
        Verifying the correctness of the information (length, format...)
         */
        if(firstName.length() > 25 || firstName.length() < 2)
        {
            if(errorLabel.getText().equals(""))
            {
                errorLabel.setText("First name must be between 2 to 25 characters long.");
            }else
            {
                errorLabel.setText(errorLabel.getText() + "\nFirst name must be between 2 to 25 characters long.");
            }

            correctInfo = false;
        }

        if(lastName.length() > 25 || lastName.length() < 2)
        {
            if(errorLabel.getText().equals(""))
            {
                errorLabel.setText("Last name must be between 2 to 25 characters long.");
            }else
            {
                errorLabel.setText(errorLabel.getText() + "\nLast name must be between 2 to 25 characters long.");
            }

            correctInfo = false;
        }

        if(email.length() > 50 || email.length() < 7 )
        {
            if(errorLabel.getText().equals(""))
            {
                errorLabel.setText("Email must be between 6 to 50 characters long");
            }else
            {
                errorLabel.setText(errorLabel.getText() + "\nEmail must be between 6 to 50 characters long.");
            }

            correctInfo = false;
        }

        if(password.length() > 25 || password.length() < 1)
        {
            if(errorLabel.getText().equals(""))
            {
                errorLabel.setText("Password must be between 1 to 25 characters long.");
            }else
            {
                errorLabel.setText(errorLabel.getText() + "\nPassword must be between 1 to 25 characters long.");
            }

            correctInfo = false;
        }

        if(phone.length() > 20)
        {
            if(errorLabel.getText().equals(""))
            {
                errorLabel.setText("Phone must not be longer than 20 characters.");
            }else
            {
                errorLabel.setText(errorLabel.getText() + "\nPhone must not be longer than 20 characters.");
            }

            correctInfo = false;
        }

        if(address.length() > 50)
        {
            if(errorLabel.getText().equals(""))
            {
                errorLabel.setText("Address must not be longer than 50 characters.");
            }else
            {
                errorLabel.setText(errorLabel.getText() + "\nAddress must not be longer than 50 characters.");
            }

            correctInfo = false;
        }



        if(correctInfo)
        {
            LoginManager lm = new LoginManager();
            try
            {
                if(lm.register(firstName, lastName, email, password, phone, address))
                {
                    try
                    {
                        gotoLogin(event);
                    }catch(IOException e){System.err.println(e.getMessage());}
                }else
                {
                    errorLabel.setText("An error happened when trying to register. Please try again.");
                }
            }catch(SQLException e)
            {
                System.err.println(e.getMessage());
            }

        }
    }

    @FXML
    public void gotoLogin(ActionEvent event) throws IOException
    {
        switchStage(event, "/view/login.fxml");
    }
}
