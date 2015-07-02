package com.model;

import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;

import com.tangosol.io.pof.PofReader;
import com.tangosol.io.pof.PofWriter;
import com.tangosol.io.pof.PortableObject;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Model class for a Person.
 *
 * @author Marco Jakob
 */
public class Person implements PortableObject, Serializable {

	  private  String Name;
	  private String Email;
	    private String Id;

    /**
     * Constructor with some initial data.
     * 
     * @param Name
     * @param Email
     */
    public Person(String Name, String Email) {
    	System.out.println("Cons2");
//        this.Name = new SimpleStringProperty(Name);
//        this.Email = new SimpleStringProperty(Email);
    	
    	this.Name=Name;
    	this.Email=Email;

        // Some initial dummy data, just for convenient testing.
//        this.Id = new SimpleStringProperty("some Id");
    	this.Id="Id";
        System.out.println("Name="+Name+";Email="+Email);
         }

    public String getName() {
    	System.out.println("NAME="+Name);
        return Name;
    }

    public void setName(String Name) {
    	System.out.println("NAME="+Name);
        this.Name=Name;
    }

    public StringProperty NameProperty() {
        return new SimpleStringProperty(Name);
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email=Email;
    }

    public StringProperty EmailProperty() {
        return new SimpleStringProperty(Email);
    }

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id=Id ;
    }

//    public StringProperty IdProperty() {
//        return Id;
//    }

	@Override
	public String toString() {
		return "Person [Name=" + Name + ", Email=" + Email + ", Id=" + Id + "]";
	}

	@Override
	public void readExternal(PofReader reader) throws IOException {
//		setName(reader.readString(0));
//		setEmail(reader.readString(1));
//		setId(reader.readString(2));
		
	}

	@Override
	public void writeExternal(PofWriter writer) throws IOException {
//		writer.writeString(0, getName());
//		writer.writeString(1, getEmail());
//		writer.writeString(2, getId());
		
	}
    
    

}