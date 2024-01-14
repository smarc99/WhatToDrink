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

        Category searchCategory = sensor.getCategory();

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

    @Override
    public ArrayList<ILocation> getAllLocations() {
        return storage.getLocations();
    }

    @Override
    public void acceptLocation(ILocation location) {
        storage.addLocation(location);
    }
}

