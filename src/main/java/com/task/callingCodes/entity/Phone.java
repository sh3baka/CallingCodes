package com.task.callingCodes.entity;


public class Phone {
    private String country;
    private String telephone;

    public Phone(String country, String telephone) {
        this.country = country;
        this.telephone = telephone;
    }

    public Phone() {
    }

    /**
     * Getter for country
     *
     * @return country
     */
    public String getCountry() {
        return country;
    }

    /**
     * Setter for country
     *
     * @param country
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Getter for telephone
     *
     * @return telephone
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * Setter for telephone
     *
     * @param telephone
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
