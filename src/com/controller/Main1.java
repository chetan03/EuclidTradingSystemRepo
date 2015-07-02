package com.controller;

import java.io.IOException;



import com.model.Person;
import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedCache;

import javafx.application.Application;
import javafx.beans.binding.Binding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main1 extends Application {

    private Stage primaryStage;
    private AnchorPane rootLayout;

    
    private ObservableList<Person> personData = FXCollections.observableArrayList();

    /**
     * Constructor
     */
    public Main1() {
        // Add some sample data
    	NamedCache cache = CacheFactory.getCache("c");
    	//Insert
    	cache.put("Vijay",  new Person("Vijay", "sgs"));
    	cache.put("Nitin",  new Person("Nitin", "ssgh"));
    	cache.put("raju",  new Person("raju", "ssjh"));
        
    	personData.add(new Person("Vijay", "sgs"));
    	personData.add(new Person("Nitin", "ssgh"));
    	personData.add(new Person("raju", "ssjh"));
    	
//        personData.add(new Person("Hans", "Muster"));
//        personData.add(new Person("Ruth", "Mueller"));
//        personData.add(new Person("Heinz", "Kurz"));
//        personData.add(new Person("Cornelia", "Meier"));
//        personData.add(new Person("Werner", "Meyer"));
//        personData.add(new Person("Lydia", "Kunz"));
//        personData.add(new Person("Anna", "Best"));
//        personData.add(new Person("Stefan", "Meier"));
//        personData.add(new Person("Martin", "Mueller")); 
//        personData.add(new Person("Pankaj", "tyagi"));
    }

    /**
     * Returns the data as an observable list of Persons. 
     * @return
     */
    public ObservableList<Person> getPersonData() {
        return personData;
    }
    
    
    
    
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("AddressApp");
        System.out.println("start...");
        initRootLayout();

   //    showPersonOverview();
    }

    /**
     * Initializes the root layout.
     */
    public void initRootLayout() {
        try {
        	 System.out.println("initRootLayout...");
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main1.class.getResource("/com/view/Overview.fxml"));
            rootLayout = (AnchorPane) loader.load();
            
            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
            
            // Give the controller access to the main app.
            PersonOverviewController controller = loader.getController();
            controller.setMain1(this);
   
            
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Returns the main stage.
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }
    
    
	public static void main(String[] args) {
        launch(args);
    }
}