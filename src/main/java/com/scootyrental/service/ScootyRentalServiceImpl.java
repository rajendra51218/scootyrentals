package com.scootyrental.service;

import com.scootyrental.model.*;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
public class ScootyRentalServiceImpl implements ScootyRentalService {

    List<Outlet> oulets;
    List<Scooty> scooties;

    // scooty block 6 secs after booking
    int scootytime = 6000;

    @PostConstruct
    public void initializeData() {
        oulets = new ArrayList<>();
        oulets.add(new Outlet(1, "SR Nagar",
                new OutletAddress("Near Metro station", "SR Nagar", "Hyderabad", "232322"), 6));
        oulets.add(new Outlet(2, "Ameerpet",
                new OutletAddress("Near Metro station", "Ameerpet", "Hyderabad", "232323"), 7));
        oulets.add(new Outlet(3, "Bharath Nagar",
                new OutletAddress("Near Metro station", "Bharath Nagar", "Hyderabad", "232324"), 8));

        scooties = new ArrayList<>();
        scooties.add(new Scooty(1, "OlA", "123", 1, ScootyStatus.AVAILABLE.name()));
        scooties.add(new Scooty(2, "OlA", "124", 1, ScootyStatus.AVAILABLE.name()));
        scooties.add(new Scooty(3, "OlA", "125", 1, ScootyStatus.AVAILABLE.name()));
        scooties.add(new Scooty(4, "Activa", "126", 2, ScootyStatus.AVAILABLE.name()));
        scooties.add(new Scooty(5, "Activa", "127", 2, ScootyStatus.AVAILABLE.name()));
        scooties.add(new Scooty(6, "Activa", "128", 2, ScootyStatus.AVAILABLE.name()));
        scooties.add(new Scooty(7, "Activa", "129", 3, ScootyStatus.AVAILABLE.name()));
        scooties.add(new Scooty(8, "OlA", "130", 3, ScootyStatus.AVAILABLE.name()));
        scooties.add(new Scooty(9, "OlA", "131", 3, ScootyStatus.AVAILABLE.name()));
        scooties.add(new Scooty(10, "OlA", "132", 3, ScootyStatus.AVAILABLE.name()));

        runTimer();
    }

    public void runTimer() {
        TimerTask task = new TimerTask() {
            public void run() {
                System.out.println("Task performed on: " + new Date());
                scooties.forEach(scooty -> {
                    if(scooty.getSootyStatus().equals(ScootyStatus.RESERVED.name())) {
                        if(scooty.getTimer() == scootytime) {
                            scooty.setSootyStatus(ScootyStatus.AVAILABLE.name());
                        }
                        scooty.setTimer(scooty.getTimer() + 1000);
                    }
                });
            }
        };
        Timer timer = new Timer("Timer");

        long delay = 1000L;
        timer.schedule(task, delay, 1000);
    }

    @Override
    public List<Outlet> getOutlets() {
        oulets.stream()
                .map(outlet -> {
                    outlet.setScooties(getByOutLetId(outlet));
                    return outlet;
                })
                .collect(Collectors.toList());
        return oulets;
    }

    @Override
    public List<Outlet> getOutletByAddress(String area) {
        return oulets.stream()
                .filter(outlet -> outlet.getOutletAddress().getAddressLineOne().contains(area) ||
                        outlet.getOutletAddress().getAddressLineTwo().contains(area))
                .map(outlet -> {
                    outlet.setScooties(getByOutLetId(outlet));
                    return outlet;
                })
                .collect(Collectors.toList());
    }

    public List<Scooty> getByOutLetId(Outlet outlet) {
        return scooties.stream()
                .filter(scooty -> scooty.getOutletId() == outlet.getId() && scooty.getSootyStatus().equals(ScootyStatus.AVAILABLE.name()))
                .collect(Collectors.toList());
    }

    @Override
    public Scooty updateScootyStatus(UpdateScootyStatusReq req) {
        AtomicReference<Scooty> scootyAtomicReference  = new AtomicReference<>();
        scooties.forEach(scooty ->  {
            if(scooty.getId() == req.getId()) {
                scooty.setSootyStatus(req.getStatus());
                scootyAtomicReference.set(scooty);
            }
        });
        return scootyAtomicReference.get();

    }

    @Override
    public List<Outlet> getOutletParkingSlots(String area) {
      return oulets.stream()
                .map(outlet -> {
                    outlet.setAvailableParkingSlots((outlet.getParkingSlots() - getScootiesByOutLetId(outlet).size()) > 0 ?  (outlet.getParkingSlots() - getScootiesByOutLetId(outlet).size()) : 0);
                    outlet.setScooties(getByOutLetId(outlet));
                    return outlet;
                })
                .collect(Collectors.toList());
    }

    public List<Scooty> getScootiesByOutLetId(Outlet outlet) {
        return scooties.stream()
                .filter(scooty -> scooty.getOutletId() == outlet.getId() && scooty.getSootyStatus().equals(ScootyStatus.AVAILABLE.name()))
                .collect(Collectors.toList());
    }


}
