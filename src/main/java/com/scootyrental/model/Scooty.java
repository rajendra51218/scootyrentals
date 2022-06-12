package com.scootyrental.model;

import lombok.Data;

@Data
public class Scooty {
    private Integer id;
    private String scootyModel;
    private String scootyNumner;
    private Integer outletId;
    private String sootyStatus;
    private Integer timer = 0;

    public Scooty(Integer id, String scootyModel, String scootyNumner, Integer outletId, String sootyStatus) {
        this.id = id;
        this.scootyModel = scootyModel;
        this.scootyNumner = scootyNumner;
        this.outletId = outletId;
        this.sootyStatus = sootyStatus;
    }
}
