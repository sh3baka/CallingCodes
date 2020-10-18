package com.task.callingCodes.entity;

import java.util.List;

public class Response {
    private String country;
    private String telephone;
    private List<Error> errorList;

    public Response(String country, String telephone, List<Error> errorList) {
        this.country = country;
        this.telephone = telephone;
        this.errorList = errorList;
    }

    public Response() {
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
     * Getter for telephone
     *
     * @return telephone
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * Getter for errorList
     *
     * @return errorList
     */
    public List<Error> getErrorList() {
        return errorList;
    }
}
