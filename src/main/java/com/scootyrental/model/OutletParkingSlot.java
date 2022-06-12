package com.scootyrental.model;

import lombok.Data;

@Data
public class OutletParkingSlot {
    private Integer outletId;
    private String outletName;
    private Integer availableParkingSlots;
}
