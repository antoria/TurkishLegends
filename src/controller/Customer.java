package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import model.entity.Demand;
import model.entity.Ingredient;
import model.entity.Kebab;
import model.entity.Recipe;
import model.manager.DemandManager;
import model.manager.IngredientManager;
import model.manager.KebabManager;
import model.manager.RecipeManager;


import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;
import java.util.ResourceBundle;

public class Customer extends Controller implements Initializable
{
    @FXML
    private Label welcomeLabel;

    @FXML
    private TextField kebabNameField;

    @FXML
    private Button newKebabButton;

    @FXML
    private Button deleteKebabButton;

    @FXML
    private Button orderKebabButton;

    @FXML
    private ListView<Kebab> listKebabs;
    private ObservableList<Kebab> listKebabsItems = FXCollections.observableArrayList();

    @FXML
    private ChoiceBox<Ingredient> breadChoice;
    private ObservableList<Ingredient> breadChoiceItems = FXCollections.observableArrayList();

    @FXML
    private ChoiceBox<Ingredient> meatChoice;
    private ObservableList<Ingredient> meatChoiceItems = FXCollections.observableArrayList();

    @FXML
    private ChoiceBox<Ingredient> vegetable1Choice;


    @FXML
    private ChoiceBox<Ingredient> vegetable2Choice;

    @FXML
    private ChoiceBox<Ingredient> vegetable3Choice;
    private ObservableList<Ingredient> vegetableChoiceItems = FXCollections.observableArrayList();

    @FXML
    private ChoiceBox<Ingredient> sauceChoice;
    private ObservableList<Ingredient> sauceChoiceItems = FXCollections.observableArrayList();

    @FXML
    private Label orderLabel;

    @FXML
    private Label descriptionKebab;

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        welcomeLabel.setText("Welcome " + Login.CURRENT_USER.getFirstName() + " " + Login.CURRENT_USER.getLastName() + "!");

        /*
        linking ListView and ChoiceBox to ObservableList
         */
        listKebabs.setItems(listKebabsItems);
        breadChoice.setItems(breadChoiceItems);
        meatChoice.setItems(meatChoiceItems);
        vegetable1Choice.setItems(vegetableChoiceItems);
        vegetable2Choice.setItems(vegetableChoiceItems);
        vegetable3Choice.setItems(vegetableChoiceItems);
        sauceChoice.setItems(sauceChoiceItems);

        /*
        Retrieving all the kebabs of the connected user and adding them to an ObservableList (in a ListView)
         */
        KebabManager km = new KebabManager();
        ArrayList<Kebab> legends;
        try
        {
            legends = km.findAllByCurrentUser();
            listKebabsItems.addAll(legends);

        }catch(SQLException e){System.err.println(e.getMessage());}


        /*
        Retrieving all the ingredients and adding them to the corresponding ChoiceBox
         */
        IngredientManager im = new IngredientManager();
        HashMap<String, ArrayList<Ingredient>> ingredients;
        try
        {
            ingredients = im.findAll();
            breadChoiceItems.addAll(ingredients.get("BREAD"));
            meatChoiceItems.addAll(ingredients.get("MEAT"));
            vegetableChoiceItems.addAll(ingredients.get("VEGETABLE"));
            sauceChoiceItems.addAll(ingredients.get("SAUCE"));

        }catch(SQLException e){System.err.println(e.getMessage());}
    }


    @FXML
    public void newKebab(ActionEvent event) throws Exception
    {
        /*
        Retrieving the information from the input fields
         */

        String name = kebabNameField.getText();

        if(kebabNameField.getText().trim().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Dialog");
            alert.setHeaderText("Kebab is Incorrect");
            alert.setContentText("Please enter a kebab name!");
            alert.showAndWait();
            //throw(new Exception());

        }else {

            Ingredient bread = breadChoice.getSelectionModel().getSelectedItem();
            Ingredient meat = meatChoice.getSelectionModel().getSelectedItem();
            Ingredient vegetable1 = vegetable1Choice.getSelectionModel().getSelectedItem();
            Ingredient vegetable2 = vegetable2Choice.getSelectionModel().getSelectedItem();
            Ingredient vegetable3 = vegetable3Choice.getSelectionModel().getSelectedItem();
            Ingredient sauce = sauceChoice.getSelectionModel().getSelectedItem();

            Recipe r = new Recipe();
            r.setBread(bread);
            r.setMeat(meat);
            r.setVegetable1(vegetable1);
            r.setVegetable2(vegetable2);
            r.setVegetable3(vegetable3);
            r.setSauce(sauce);
            r.setPrice(bread.getPrice() + meat.getPrice() + sauce.getPrice() + vegetable1.getPrice() + vegetable2.getPrice() + vegetable3.getPrice());

        /*
        Creating a Recipe to link to a Kebab object
         */
            RecipeManager rm = new RecipeManager();
            try {
                rm.add(r);

                Kebab k = new Kebab();
                k.setName(name);
                k.setRecipe(r);

            /*
            Creating a Kebab and refreshing the ListView
             */
                KebabManager km = new KebabManager();
                try {
                    km.add(k);
                    listKebabsItems.add(k);
                    listKebabs.refresh();

                } catch (SQLException e) {
                    System.err.println(e.getMessage());
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
    }


    @FXML
    public void deleteKebab(ActionEvent event)
    {
        KebabManager km = new KebabManager();
        try
        {
            /*
            Retrieving an instance of the selected kebab in the ListView
             */
            Kebab k = listKebabs.getSelectionModel().getSelectedItem();
            km.delete(k);

            listKebabsItems.remove(k);
            listKebabs.getSelectionModel().clearSelection();
            listKebabs.refresh();

        }catch(SQLException e){
            System.err.println(e.getMessage());
        }
    }

    @FXML
    public void orderKebab(ActionEvent event)
    {
        DemandManager dm = new DemandManager();
        try
        {
            Demand d = new Demand();
            Kebab k = listKebabs.getSelectionModel().getSelectedItem();
            d.setKebab(k);
            if(dm.add(d))
            {
                orderLabel.setTextFill(Color.FORESTGREEN);
                orderLabel.setText("Ordered " + k.getName() + " successfully !");

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Payment Confirmation");
                alert.setHeaderText("Choose your mean of payment");
                alert.setContentText("Credit Card or Cash at delivery");

                ButtonType buttonTypeOne = new ButtonType("Cash at Delivery");
                ButtonType buttonTypeTwo = new ButtonType("Credit Card");
                ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

                alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo, buttonTypeCancel);

                Optional<ButtonType> result = alert.showAndWait();

                if (result.get() == buttonTypeOne){
                    Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                    alert2.setTitle("Information");
                    alert2.setHeaderText("Payment Accepted");
                    alert2.setContentText("Your cash payment has been accepted !");
                    alert2.showAndWait();

                } else if (result.get() == buttonTypeTwo) {

                    TextInputDialog dialog = new TextInputDialog("");
                    dialog.setTitle("Credit Card registration");
                    dialog.setHeaderText("New Credit Card");
                    dialog.setContentText("Please enter your credit card number:");
                    Optional<String> ccnumber = dialog.showAndWait();
                    if (ccnumber.isPresent()){
                        System.out.println("Your card number is registered as : " + ccnumber.get());
                    }

                } else {
                    orderLabel.setTextFill(Color.DARKRED);
                    orderLabel.setText("An error happened while ordering. Please try again.");
                }

            }else
            {
                orderLabel.setTextFill(Color.DARKRED);
                orderLabel.setText("An error happened while ordering. Please try again.");
            }

        }catch(SQLException e)
        {
            System.err.println(e.getMessage());
        }
    }

    @FXML
    public void listClicked(MouseEvent e)
    {
        /*
        Displays a description of the selected kebab
         */

        Kebab k = listKebabs.getSelectionModel().getSelectedItem();
        if (k != null) {

            Recipe r = k.getRecipe();

            StringBuilder sb = new StringBuilder();
            sb.append(r.getBread().getName());
            sb.append(", ");
            sb.append(r.getMeat().getName());
            sb.append(", ");
            sb.append(r.getVegetable1().getName());
            sb.append(", ");
            sb.append(r.getVegetable2().getName());
            sb.append(", ");
            sb.append(r.getVegetable3().getName());
            sb.append(", ");
            sb.append(r.getSauce().getName());
            sb.append(", ");
            sb.append(r.getPrice());
            sb.append(" Euros");

            descriptionKebab.setText(sb.toString());
        }
    }
}
