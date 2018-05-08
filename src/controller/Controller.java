package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Controllers are linked to the FXML views.
 */
public class Controller
{
    /**
     *
     * @param event
     * @param path FXML stage to load
     * @throws IOException
     */
    public void switchStage(ActionEvent event, String path) throws IOException
    {
        Parent p = FXMLLoader.load(getClass().getResource(path));
        Scene scene = new Scene(p);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
