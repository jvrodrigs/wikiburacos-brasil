package com.wiki.backend.Model;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotEmpty;

@Embeddable
public class Address {
    @NotEmpty(message = "Street field is required")
    private String street;
    @NotEmpty(message = "Neighborhood field is required")
    private String neighborhood;
    @NotEmpty(message = "City field is required")
    private String city;
    @NotEmpty(message = "State field is required")
    private String state;
    private String number;
    private String complement;
    @NotEmpty(message = "CEP field is required")
    private String cep;

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }
}
