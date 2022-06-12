package com.scootyrental.service;

import com.scootyrental.model.Outlet;
import com.scootyrental.model.OutletParkingSlot;
import com.scootyrental.model.Scooty;
import com.scootyrental.model.UpdateScootyStatusReq;

import java.util.List;

public interface ScootyRentalService {

    List<Outlet> getOutlets();

    List<Outlet> getOutletByAddress(String area);

    Scooty updateScootyStatus(UpdateScootyStatusReq req);

    List<Outlet> getOutletParkingSlots(String area);
}
