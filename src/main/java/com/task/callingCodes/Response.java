package com.task.callingCodes;

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

    public List<Error> getErrorList() {
        return errorList;
    }

    public void setErrorList(List<Error> errorList) {
        this.errorList = errorList;
    }
}
