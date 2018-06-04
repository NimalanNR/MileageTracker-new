package com.example.tnfdev.mileagetracker;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Contacts {

    ArrayList<contacts_data> contacts;

    public ArrayList<contacts_data> getContacts() {
        return contacts;
    }

    public void setContacts(ArrayList<contacts_data> contacts) {
        this.contacts = contacts;
    }

    public class contacts_data {
        String name;
        String email;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
    }
}
