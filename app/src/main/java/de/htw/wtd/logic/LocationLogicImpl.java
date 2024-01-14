package de.htw.wtd.logic;

import de.htw.wtd.data.ILocation;
import de.htw.wtd.data.ILocationStorage;
import de.htw.wtd.data.LocationImpl;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

public class LocationLogicImpl implements ILocationLogic{
    private ILocationStorage storage;
    private ISensorLogic sensor;

    private ArrayList<IPlace> places;

    public LocationLogicImpl(ILocationStorage storage, ISensorLogic sensor){
        this.storage = storage;
        this.sensor = sensor;
    }

    /**
     * Ladet alle Locations, welche ausgewählt werden können, aus einer JSON-Datei, welche im assets liegt.
     */
    private void setup(){
        File file = new File("assets/locations.json");
        //extract data from file
        try (FileInputStream fis = new FileInputStream(file)) {

        } catch (Exception e) {
            System.err.println("Error while reading locations.json");
        }

    }

    @Override
    public ILocation getNewLocation() {
        ILocation loc = new LocationImpl();
        loc.setTime(System.currentTimeMillis());
        return loc;
    }

    @Override
    public ArrayList<ILocation> getAllLocations() {
        return storage.getLocations();
    }
}
