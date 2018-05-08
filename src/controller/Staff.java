package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import model.entity.Demand;
import model.manager.DemandManager;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Staff extends Controller implements Initializable
{
    @FXML
    private ListView<Demand> demandList;
    private ObservableList<Demand> listDemandItems = FXCollections.observableArrayList();


    @FXML
    public void setSelectedPreviousStep(ActionEvent e) throws SQLException
    {
        Demand d = demandList.getSelectionModel().getSelectedItem();
        int currentStatus = d.getStatus();
        // if we can go back
        if(currentStatus > Demand.WAITING)
        {
            d.setStatus(currentStatus - 1);
            DemandManager dm = new DemandManager();
            if(dm.updateStatus(d))
            {
                demandList.refresh();
            }
        }
    }

    @FXML
    public void setSelectedNextStep(ActionEvent e) throws SQLException
    {
        Demand d = demandList.getSelectionModel().getSelectedItem();
        int currentStatus = d.getStatus();
        // if we can go further
        if(currentStatus < Demand.COMPLETED)
        {
            d.setStatus(currentStatus + 1);
            DemandManager dm = new DemandManager();
            if(dm.updateStatus(d))
            {
                demandList.refresh();
            }
        }
    }

    @FXML
    public void removeDemand(ActionEvent e) throws SQLException
    {
        Demand d = demandList.getSelectionModel().getSelectedItem();
        DemandManager dm = new DemandManager();

        if(dm.delete(d))
        {
            listDemandItems.remove(d);
            demandList.refresh();
        }
    }

    @FXML
    public void refreshList(ActionEvent e) throws SQLException
    {
        /*
        Refresh the list of orders
         */
        demandList.setItems(listDemandItems);
        DemandManager dm = new DemandManager();

        ArrayList<Demand> demands;
        demands = dm.findAll();
        listDemandItems.removeAll();
        listDemandItems.clear();
        listDemandItems.addAll(demands);

        demandList.refresh();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        try
        {
            refreshList(new ActionEvent());
        }catch(SQLException e)
        {
            System.err.println(e.getMessage());
        }

    }
}
