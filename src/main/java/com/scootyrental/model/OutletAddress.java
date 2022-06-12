package com.scootyrental.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
public class OutletAddress {

    private String addressLineOne;
    private String addressLineTwo;
    private String city;
    private String pinCode;

    public OutletAddress(String addressLineOne, String addressLineTwo, String city, String pinCode) {
        this.addressLineOne = addressLineOne;
        this.addressLineTwo = addressLineTwo;
        this.city = city;
        this.pinCode = pinCode;
    }
}
