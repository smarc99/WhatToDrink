package de.htw.wtd.logic;

import de.htw.wtd.data.ILocation;
import de.htw.wtd.data.ILocationStorage;
import de.htw.wtd.data.LocationImpl;

import java.util.ArrayList;
import java.util.List;

public class LocationLogicImpl implements ILocationLogic{
    private ILocationStorage storage;
    private ISensorLogic sensor;

    private List<IPlace> places;

    public LocationLogicImpl(ILocationStorage storage, ISensorLogic sensor, List<IPlace> places){
        this.storage = storage;
        this.sensor = sensor;
        this.places = places;
    }

    @Override
    public ILocation getNewLocation() {

        if (places == null || places.isEmpty()) {
            return null;
        }

        Category searchCategory = getCategory();

        IPlace place = null;
        for (IPlace p : places) {
            if (p.getCategory() == searchCategory) {
                place = p;
                break;
            }
        }

        if (place == null) {
            return null;
        }
        String desc = place.getName() + " -> " + place.getCategory().toString();
        ILocation loc = new LocationImpl(place.getLongitude(), place.getLatitude(), desc);
        loc.setTime(System.currentTimeMillis());
        return loc;
    }



    /**
     * Ermittelt die Kategorie anhand der Sensordaten.
     * @return Kategorie
     */
    private Category getCategory() {
        double temp = sensor.getTemperatur();
        double light = sensor.getLightLevel();

        Category searchCategory;
        if (temp < 15) {
            if(light < 1000) {
                searchCategory = Category.RESTAURANT;
            } else {
                searchCategory = Category.COFFEESHOP;
            }
        } else {
            if(light < 1000) {
                searchCategory = Category.BAR;
            } else {
                searchCategory = Category.CREAMICESHOP;
            }
        }
        return searchCategory;
    }

    @Override
    public ArrayList<ILocation> getAllLocations() {
        return storage.getLocations();
    }

    @Override
    public void acceptLocation(ILocation location) {
        storage.addLocation(location);
    }
}

