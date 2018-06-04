package com.example.tnfdev.mileagetracker.model;

public class Vehicles {

//    {"Users_Id":1001,"Bike_Id":101,"Bike_Model":"Pulsar","Existing_Fuel":3,"Fuel_Refil":5,"Fuel_Left":1,"Amount_Paid":400,"Initial_Odometer_Reading":1950,"Current_Odometer_Reading":2212}

    String Users_Id;
    String Bike_Id;
    String Bike_Model;
    String Existing_Fuel;
    String Fuel_Refil;
    String Fuel_Left;
    String Amount_Paid;
    String Initial_Odometer_Reading;
    String Current_Odometer_Reading;

    public Vehicles(String Users_Id,String Bike_Id,String Bike_Model,String Existing_Fuel,String Fuel_Refil,String Fuel_Left
    ,String Amount_Paid,String Initial_Odometer_Reading,String Current_Odometer_Reading) {
        this.Users_Id = Users_Id;
        this.Bike_Id = Bike_Id;
        this.Bike_Model = Bike_Model;
        this.Existing_Fuel = Existing_Fuel;
        this.Fuel_Refil = Fuel_Refil;
        this.Fuel_Left = Fuel_Left;
        this.Amount_Paid = Amount_Paid;
        this.Initial_Odometer_Reading = Initial_Odometer_Reading;
        this.Current_Odometer_Reading = Current_Odometer_Reading;
    }

    public Vehicles(){

    }

    public String getUsers_Id() {
        return Users_Id;
    }

    public void setUsers_Id(String users_Id) {
        Users_Id = users_Id;
    }

    public String getBike_Id() {
        return Bike_Id;
    }

    public void setBike_Id(String bike_Id) {
        Bike_Id = bike_Id;
    }

    public String getBike_Model() {
        return Bike_Model;
    }

    public void setBike_Model(String bike_Model) {
        Bike_Model = bike_Model;
    }

    public String getExisting_Fuel() {
        return Existing_Fuel;
    }

    public void setExisting_Fuel(String existing_Fuel) {
        Existing_Fuel = existing_Fuel;
    }

    public String getFuel_Refil() {
        return Fuel_Refil;
    }

    public void setFuel_Refil(String fuel_Refil) {
        Fuel_Refil = fuel_Refil;
    }

    public String getFuel_Left() {
        return Fuel_Left;
    }

    public void setFuel_Left(String fuel_Left) {
        Fuel_Left = fuel_Left;
    }

    public String getAmount_Paid() {
        return Amount_Paid;
    }

    public void setAmount_Paid(String amount_Paid) {
        Amount_Paid = amount_Paid;
    }

    public String getInitial_Odometer_Reading() {
        return Initial_Odometer_Reading;
    }

    public void setInitial_Odometer_Reading(String initial_Odometer_Reading) {
        Initial_Odometer_Reading = initial_Odometer_Reading;
    }

    public String getCurrent_Odometer_Reading() {
        return Current_Odometer_Reading;
    }

    public void setCurrent_Odometer_Reading(String current_Odometer_Reading) {
        Current_Odometer_Reading = current_Odometer_Reading;
    }
}
