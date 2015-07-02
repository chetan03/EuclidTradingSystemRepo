package com.controller;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;







import com.model.Person;
import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedCache;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class PersonOverviewController {
	@FXML
	private TableView<Person> personTable;
	@FXML
	private TableColumn<Person, String> firstNameColumn;
	@FXML
	private TableColumn<Person, String> EmailColumn;
	@FXML
	private Label firstNameLabel;
	@FXML
	private Label EmailLabel;
	@FXML
	private Label idLabel;

	static ObservableList<Person> personData = FXCollections
			.observableArrayList();
	// Reference to the main application.
	private Main1 main1;

	/**
	 * The constructor. The constructor is called before the initialize()
	 * method.
	 */
	public PersonOverviewController() {
	}

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {
		System.out.println("Initialize...");

		// Initialize the person table with the two columns.
		firstNameColumn.setCellValueFactory(cellData -> cellData.getValue()
				.NameProperty());
		EmailColumn.setCellValueFactory(cellData -> cellData.getValue()
				.EmailProperty());
		
		personTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Person>() {

			@Override
			public void changed(ObservableValue<? extends Person> observable,
					Person oldValue, Person newValue) {
				showPersonDetails(newValue);
				
			}
		});
	}

	
	private void showPersonDetails(Person person) {

		// use setText(...) on all labels with info from the person object
		// use setText("") on all labels if the person is null
		
	firstNameLabel.setText(person.getName());
	EmailLabel.setText(person.getEmail());
	idLabel.setText(person.getId());
	}
	
	@FXML
	private void test(ActionEvent event) {

		System.out.println("IN test...");
		CacheFactory.ensureCluster();
		NamedCache cache = CacheFactory.getCache("c");
		Random randomGenerator = new Random();

		int randomInt = randomGenerator.nextInt(1000);

		// Insert
		System.out.println(randomInt);
		String st = "XXX" + String.valueOf(randomInt);
		cache.put(
				String.valueOf(randomInt),
				new Person(String.valueOf(randomInt), "test"+String.valueOf(randomInt)));
		personTable.getItems()
				.add(new Person(String.valueOf(randomInt), "test"+String.valueOf(randomInt)));

	}

	static NamedCache cacheContacts = CacheFactory.getCache("c",
			Person.class.getClassLoader());

	@FXML
	private void refresh(ActionEvent event) {
		CacheFactory.ensureCluster();
		NamedCache cache = CacheFactory.getCache("c");

		Set pd = cache.keySet();

		personTable.getItems().clear();

		for (Iterator iterator = pd.iterator(); iterator.hasNext();) {
			String object = (String) iterator.next();

			personTable.getItems().add((Person) cache.get(object));
			System.out.println(object);

		}

	}

	@FXML
	private void handleDeletePerson() {
		CacheFactory.ensureCluster();
		NamedCache cache = CacheFactory.getCache("c");
		Person person = personTable.getSelectionModel().getSelectedItem();
		try {

			cache.remove(person.getName());

			personTable.getItems().remove(person);
			System.out.println("in delete handler");
		} catch(NullPointerException e) {
			// Nothing selected
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Warning Dialog");
			alert.setHeaderText("Look, a Warning Dialog");
			alert.setContentText("Please select the Person to delete");

			alert.showAndWait();
		}

	}

	public static void populateCache(NamedCache c) {
		if (c.isEmpty()) {
			System.out.println("list is empty");
			Map mapBuffer = new HashMap();
			for (int i = 0; i < 10; ++i) {

				if (!mapBuffer.isEmpty()) {
					c.putAll(mapBuffer);
				}

				Collection<Person> collection = (Collection<Person>) cacheContacts
						.values();
				System.out.println("--------------------------------------- "
						+ collection);

				/*
				 * Iterable<Person> itr = (Iterable<Person>)
				 * collection.iterator(); while (((Iterator<Person>)
				 * itr).hasNext()) { System.out.println(((Iterator<Person>)
				 * itr).next()); }
				 */
				/*
				 * this.personData.clear(); this.personData.addAll(collection);
				 */
				System.out.println(personData);
			}
		}
	}

	/**
	 * Is called by the main application to give a reference back to itself.
	 * 
	 * @param mainApp
	 */
	public void setMain1(Main1 main1) {
		this.main1 = main1;

		// System.out.println("main1.personData=" + main1.getPersonData());

		for (Person p : main1.getPersonData()) {
			System.out.println(p);
		}

		// Add observable list data to the table
		personTable.setItems(main1.getPersonData());

		System.out.println("PersonData set!");
	}
}
