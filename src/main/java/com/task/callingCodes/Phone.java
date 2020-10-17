package com.task.callingCodes;

import java.util.List;

public class Phone {
    private String country;
    private String telephone;

    public Phone(String country, String telephone) {
        this.country = country;
        this.telephone = telephone;
    }

    public Phone() {
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
