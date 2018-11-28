package project.aut.carato;

import android.os.Parcelable;

import java.io.Serializable;

public class Car implements Serializable {
    String name;
    String model;
    String type;
    String seats;
    String doors;
    String transmission;
    String rent;
    byte[] image;
    String color;
    String cClass;

    public Car (String name, String model, String type, String seats, String doors, String transmission, String rent, byte[] image, String color, String cClass) {
        this.name = name;
        this.model = model;
        this.type = type;
        this.seats = seats;
        this.doors = doors;
        this.transmission = transmission;
        this.rent = rent;
        this.image = image;
        this.color = color;
        this.cClass = cClass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSeats() {
        return seats;
    }

    public void setSeats(String seats) {
        this.seats = seats;
    }

    public String getDoors() {
        return doors;
    }

    public void setDoors(String doors) {
        this.doors = doors;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public String getRent() {
        return rent;
    }

    public void setRent(String rent) {
        this.rent = rent;
    }


    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getcClass() {
        return cClass;
    }

    public void setcClass(String cClass) {
        this.cClass = cClass;
    }
}
