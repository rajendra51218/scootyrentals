package com.scootyrental.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
public class Outlet {
    private Integer id;
    private String outletName;
    private OutletAddress outletAddress;
    private List<Scooty> scooties;
    private Integer occupiedParkingSlots;
    private Integer availableParkingSlots;
    private Integer parkingSlots;

    public Outlet(Integer id, String outletName, OutletAddress outletAddress, Integer parkingSlots) {
        this.id = id;
        this.outletName = outletName;
        this.outletAddress = outletAddress;
        this.parkingSlots = parkingSlots;
    }
}
