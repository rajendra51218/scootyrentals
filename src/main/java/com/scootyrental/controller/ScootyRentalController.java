package com.scootyrental.controller;

import com.scootyrental.model.Outlet;
import com.scootyrental.model.OutletParkingSlot;
import com.scootyrental.model.Scooty;
import com.scootyrental.model.UpdateScootyStatusReq;
import com.scootyrental.service.ScootyRentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/scooty-rentals")
public class ScootyRentalController {

    @Autowired
    ScootyRentalService scootyRentalService;

    // Get All outlets
    @GetMapping("/outlets")
    public List<Outlet> getOutlets() {
         return  scootyRentalService.getOutlets();
    }

    // get outlets based on area otherwise nearest area
    @GetMapping("/outlets/{area}")
    public List<Outlet> getOutletsByAddress(@PathVariable String area) {
        return scootyRentalService.getOutletByAddress(area);
    }

    //updating Status of Scooty like available and reserved
    @PutMapping("/scooty/status")
    public Scooty updateScootyStatus(@RequestBody UpdateScootyStatusReq req) {
        return scootyRentalService.updateScootyStatus(req);
    }

    // checking avialable parking slots before parking the scooty
    @GetMapping("/availableParkingSlots/{area}")
    public List<Outlet> getAvailableParkingSlots(@PathVariable String area) {
         return scootyRentalService.getOutletParkingSlots(area);
    }

}
